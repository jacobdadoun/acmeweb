package com.acme.statusmgr.beans;

import com.acme.servermgr.ServerStatusDecorator;

public class ExtensionStatus extends ServerStatusDecorator {

    public ExtensionStatus(StatusInterface statusInterface) {
        super(statusInterface);
        setExtensionsStatusDesc();
    }
}
