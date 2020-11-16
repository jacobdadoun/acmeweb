package com.acme.statusmgr.beans;

import com.acme.servermgr.ServerManager;
import com.acme.servermgr.ServerStatusDecorator;

public class MemoryStatus extends ServerStatusDecorator {

    public MemoryStatus(StatusInterface statusInterface) {
        super(statusInterface);
    }

    @Override
    public long getId(){
        return super.getId();
    }

    @Override
    public String getContentHeader(){
        return super.getContentHeader();
    }

    @Override
    public String getStatusDesc(){
        return super.getStatusDesc() + ServerManager.getMemoryStatusDesc();
    }
}
