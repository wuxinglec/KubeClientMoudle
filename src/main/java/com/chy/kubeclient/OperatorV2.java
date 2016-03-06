package com.chy.kubeclient;

import com.sun.corba.se.spi.activation.IIOP_CLEAR_TEXT;

import java.util.Map;

/**
 * Created by chyzh on 2016/3/6.
 */
public class OperatorV2 implements OperatorInterfaceV2{
    private JerseyClient _client = null;

    public OperatorV2(String serverUrl){
        _client = new JerseyClient(serverUrl);
    }

    public OperatorV2() {
        _client = new JerseyClient("http://zhaochenyang.space:8080/api/v1");
    }

    public String createResource(String namespace, ResourceType resourceType, String jsonStr) {
        Params params = new Params();
        params.setNamespace(namespace);
        params.setResourceType(resourceType);
        params.setJson(jsonStr);
        return _client.create(params);
    }

    public String createResource(ResourceType resourceType, String jsonStr) {
        return createResource("default", resourceType, jsonStr);
    }

    public String getResourceList(String namespace, ResourceType resourceType) {
        Params params = new Params();
        params.setResourceType(resourceType);
        params.setNamespace(namespace);
        return _client.list(params);
    }

    public String getResourceList(ResourceType resourceType) {
        return getResourceList("default", resourceType);
    }

    public String getResourceInfo(String namespace, ResourceType resourceType, String resourceName) {
        Params params = new Params();
        params.setNamespace(namespace);
        params.setResourceType(resourceType);
        params.setName(resourceName);
        return _client.get(params);
    }

    public String getResourceInfo(ResourceType resourceType, String resourceName) {
        return getResourceInfo("default", resourceType, resourceName);
    }

    public String deleteResource(String namespace, ResourceType resourceType, String resourceName) {
        Params params = new Params();
        params.setNamespace(namespace);
        params.setResourceType(resourceType);
        params.setName(resourceName);
        return _client.delete(params);
    }

    public String deleteResource(ResourceType resourceType, String resourceName) {
        return deleteResource("default", resourceType, resourceName);
    }

    public String getPodLog(String namespace, String podName, Map<String, String> options) {
        Params params = new Params();
        params.setNamespace(namespace);
        params.setResourceType(ResourceType.PODS);
        params.setName(podName);
        params.setOptions(options);
        return _client.get(params);
    }

    public String getPodLog(String podName, Map<String, String> options) {
        return getPodLog("default", podName, options);
    }

    public void close() {
        this._client.close();
    }
}
