package com.chy.kubeclient;

/**
 * Created by chyzh on 2016/3/5.
 */
public enum ResourceType {
    NODES("nodes"),
    NAMESPACES("namespaces"),
    SERVICES("services"),
    REPLICATIONCONTROLLERS("replicationcontrollers"),
    PODS("pods"),
    BINDINGS("bindings"),
    ENDPOINTS("endpoints"),
    SERVICEACCOUNTS("serviceaccounts"),
    SECRETS("secrets"),
    ENENTS("events"),
    COMPONENTSTATUSES("componentstatuses"),
    LIMITRANGES("limitranges"),
    RESOURCEQUOTAS("resourcequotas"),
    PODTEMPLATES("podtemplates"),
    PERSISTENTVOLUMECLAIMS("persistentvolumeclaims"),
    PERSISTENTVOLUMES("persistentvolumes");

    private String type;

    private ResourceType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
