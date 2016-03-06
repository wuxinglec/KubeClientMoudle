package com.chy.kubeclient;

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
        if (resourceType != ResourceType.NAMESPACES)
            return createResource("default", resourceType, jsonStr);
        else
            return createResource(null, resourceType, jsonStr);
    }

    public String getResourceList(String namespace, ResourceType resourceType) {
        Params params = new Params();
        params.setResourceType(resourceType);
        params.setNamespace(namespace);
        return _client.list(params);
    }

    public String getResourceList(ResourceType resourceType) {
        if (resourceType != ResourceType.NAMESPACES)
            return getResourceList("default", resourceType);
        else
            return getResourceList(null, resourceType);
    }

    public String getResourceInfo(String namespace, ResourceType resourceType, String resourceName) {
        Params params = new Params();
        params.setNamespace(namespace);
        params.setResourceType(resourceType);
        params.setName(resourceName);
        return _client.get(params);
    }

    public String getResourceInfo(ResourceType resourceType, String resourceName) {
        if (resourceType != ResourceType.NAMESPACES)
            return getResourceInfo("default", resourceType, resourceName);
        else
            return getResourceInfo(null, resourceType, resourceName);
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

    public String updateResourceWithMergePatch(String namespace, ResourceType resourceType, String resourceName, String jsonStr) {
        Params params = new Params();
        params.setNamespace(namespace);
        params.setResourceType(resourceType);
        params.setName(resourceName);
        params.setJson(jsonStr);
        return _client.updateWithMediaType(params, "application/merge-patch+json");
    }

    public String updateResourceWithMergePatch(ResourceType resourceType, String resourceName, String jsonStr) {
        if(resourceType != ResourceType.NAMESPACES)
            return updateResourceWithMergePatch("default", resourceType, resourceName, jsonStr);
        else
            return updateResourceWithMergePatch(null, resourceType, resourceName, jsonStr);
    }

    public String updateResourceWithStrategicMergePatch(String namespace, ResourceType resourceType, String resourceName, String jsonStr) {
        Params params = new Params();
        params.setNamespace(namespace);
        params.setResourceType(resourceType);
        params.setName(resourceName);
        params.setJson(jsonStr);
        return _client.updateWithMediaType(params, "application/strategic-merge-patch+json");
    }

    public String updateResourceWithStrategicMergePatch(ResourceType resourceType, String resourceName, String jsonStr) {
        if(resourceType != ResourceType.NAMESPACES)
            return updateResourceWithStrategicMergePatch("default", resourceType, resourceName, jsonStr);
        else
            return updateResourceWithStrategicMergePatch(null, resourceType, resourceName, jsonStr);
    }

    public void close() {
        this._client.close();
    }
}
