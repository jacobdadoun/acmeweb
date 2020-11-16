package com.acme.statusmgr;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.acme.statusmgr.beans.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for all web/REST requests about the status of servers
 *
 * For initial school project - just handles info about this server
 * Syntax for URLS:
 *    All start with /server
 *    /status  will give back status of server
 *    a param of 'name' specifies a requestor name to appear in response
 *
 * Examples:
 *    http://localhost:8080/server/status
 *
 *    http://localhost:8080/server/status?name=Noach
 *
 *
 *
 */
@RestController
@RequestMapping("/server")
public class StatusController {

    protected static final String template = "Server Status requested by %s";
    protected final AtomicLong counter = new AtomicLong();

    /**
     *
     * @param name Supports HTML output a the name in the given URL. Overrides "Anonymous".
     * @param details optional request parameter, which accepts a comma separated list of strings to be output via console.
     * @return returns ServerStatus implementation with 'counter' and 'name'
     */
    @RequestMapping("/status")
    public ServerStatus getServerStatusInfo(@RequestParam(value="name", defaultValue="Anonymous") String name,
                                            @RequestParam(value="details", required = false) List<String> details) {

        if(details != null){
            for(String str: details){
                System.out.println("*** DEBUG INFO *** ::: " + str);
            }
        }

        return new ServerStatus(counter.incrementAndGet(),
                            String.format(template, name));
    }

    // http://localhost:8080/server/status/detailed?details=operations,extensions,memory
    // {"id":3,"contentHeader":"Server Status requested by Anonymous","statusDesc":"Server is up, and is operating normally, and is using these extensions - [Hypervisor, Kubernetes, RAID-6], and its memory is running low"}

    // http://localhost:8080/server/status/detailed?details=operations,extensions,memory&name=Noach




    // Todo --> Parse through the list of desired details.
    //  Then have a switch statement which will create its decorator. When we call the constructor,
    @RequestMapping("status/detailed")
    public StatusInterface getDetailedInfo(@RequestParam(value="name", defaultValue="Anonymous",
            required = false) String name, @RequestParam(value="details") List<String> details){

        StatusInterface statusBuilder = new ServerStatus(counter.incrementAndGet(), String.format(template, name));



        if(details != null){

            for (String str : details) {
                System.out.println("***DEBUG INFO *** ::: " + str);

                if(str.toLowerCase().equals("operations")){
                    statusBuilder = new OperationStatus(statusBuilder);
                }
                if(str.toLowerCase().equals("extensions")){
                    statusBuilder = new ExtensionStatus(statusBuilder);
                }
                if(str.toLowerCase().equals("memory")){
                    statusBuilder = new MemoryStatus(statusBuilder);
                }
            }

        }
        else{
            //TODO --> Define exceptionss. with or without HTTPStatus.Bad_Request?
            Throw new DetailsException();
        }



        return statusBuilder;
    }
}
