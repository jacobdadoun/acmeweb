package com.acme.statusmgr;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.acme.statusmgr.beans.ServerStatus;
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
}
