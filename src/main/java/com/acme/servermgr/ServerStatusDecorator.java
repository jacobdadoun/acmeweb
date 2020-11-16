package com.acme.servermgr;

import com.acme.statusmgr.beans.StatusInterface;

/**
 * Manage all servers (service providers) being tracked by the Acme server tracking system
 * For now just some simple static methods for use in school project.
 * Treat this as a 'black box' that gives back indicators like up, running etc for
 * various 'services' that are being managed.
 */

// Todo --> ServerManager needs to be extended. Because right now it returns only a basic status. The word "up." BORING!
//  Objective: Present server status as requested in Mod-17. The subclass will just add on "baby method" to return
//  a string that will be presented by the browser (via Spring).

/**
 * Todo --> Implement realistic outputs in the future with the following strings in the decorator subclasses
 * static public final String operationsStatusMessage = ", and is operating %s";
 * static public final String extensionsStatusMessage = ", and is using these extensions - %s";
 * static public final String memoryStatusMessage = ", and is operating %s";
 */

public abstract class ServerStatusDecorator implements StatusInterface {

    protected StatusInterface tempStatusInterface;

    public ServerStatusDecorator(StatusInterface tempStatusInterface){
        this.tempStatusInterface = tempStatusInterface;
    }

    /**
     * Find out if this server is operating normally
     * @return Boolean indicating if server is operating normally
     */
    static public Boolean isOperatingNormally()
    {
        return true;
    }


    /**
     * Get the status of this server
     * @return a descriptive string about the servers status
     */
    @Override
    public long getId(){
        return tempStatusInterface.getId();
    }

    @Override
    public String getContentHeader(){
        return tempStatusInterface.getContentHeader();
    }

    @Override
    public String getStatusDesc(){
        return tempStatusInterface.getStatusDesc();
    }

}
