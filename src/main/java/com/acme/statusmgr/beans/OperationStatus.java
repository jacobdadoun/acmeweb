package com.acme.statusmgr.beans;

import com.acme.servermgr.ServerManager;
import com.acme.servermgr.ServerStatusDecorator;

public class OperationStatus extends ServerStatusDecorator {

    public OperationStatus(StatusInterface statusInterface) {
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
