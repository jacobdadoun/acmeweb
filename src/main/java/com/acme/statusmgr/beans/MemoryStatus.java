package com.acme.statusmgr.beans;

import com.acme.servermgr.ServerStatusDecorator;

public class MemoryStatus extends ServerStatusDecorator {

    public MemoryStatus(StatusInterface statusInterface) {
        super(statusInterface);
        setMemoryStatusDesc();
    }
}
