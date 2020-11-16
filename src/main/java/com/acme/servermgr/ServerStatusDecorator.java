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

    static protected StatusInterface tempStatusInterface;
    static protected String statusMessage = "Unknown";

    public ServerStatusDecorator(StatusInterface statusInterface){
        tempStatusInterface = statusInterface;
        statusMessage = tempStatusInterface.getStatusDesc();
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
     * Set the status of this server
     * @param statusDesc Status message for a request is passed here and assigned to the server object
     */
    @Override
    public void setStatusDesc(String statusDesc) {
        tempStatusInterface.setStatusDesc(statusDesc);
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


    static public void setOperationStatusDesc() {
        tempStatusInterface.setStatusDesc(tempStatusInterface.getStatusDesc() + ", and is operating normally");
    }
    static public void setExtensionsStatusDesc(){
        tempStatusInterface.setStatusDesc(tempStatusInterface.getStatusDesc() + ", and is using these extensions - [Hypervisor, Kubernetes, RAID-6]");
    }

    static public void setMemoryStatusDesc(){
        tempStatusInterface.setStatusDesc(tempStatusInterface.getStatusDesc() + ", and its memory is running low");
    }
}
