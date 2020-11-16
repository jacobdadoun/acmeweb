package com.acme.servermgr;

/**
 * ServerManager provides the decorators with the string status message desired.
 * When a decorator calls a method of this class, that method returns a string for that decorator's status.
 */

public class ServerManager {

    static public String getServerStatusDesc(){
        return " up";
    }

    static public String getOperationStatusDesc() {
        return ", and is operating normally";
    }

    static public String getExtensionsStatusDesc(){
        return ", and is using these extensions - [Hypervisor, Kubernetes, RAID-6]";
    }

    static public String getMemoryStatusDesc(){
        return  ", and its memory is running low";
    }
}
