package com.acme.statusmgr.beans;

import com.acme.servermgr.ServerStatusDecorator;

public class OperationStatus extends ServerStatusDecorator {

    public OperationStatus(StatusInterface statusInterface) {
        super(statusInterface);
        setOperationStatusDesc();
    }

}
