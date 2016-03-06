package com.chy.kubeclient;

import java.util.Map;

/**
 * Created by chyzh on 2016/3/6.
 */
public interface OperatorInterfaceV2 {
    public String createResource(String namespace, ResourceType resourceType, String jsonStr);
    public String createResource(ResourceType resourceType, String jsonStr);

    public String getResourceList(String namespace, ResourceType resourceType);
    public String getResourceList(ResourceType resourceType);

    public String getResourceInfo(String namespace, ResourceType resourceType, String resourceName);
    public String getResourceInfo(ResourceType resourceType, String resourceName);

    public String deleteResource(String namespace, ResourceType resourceType, String resourceName);
    public String deleteResource(ResourceType resourceType, String resourceName);

    public String getPodLog(String namespace, String podName, Map<String, String> options);
    public String getPodLog(String podName, Map<String, String> options);

    public String updateResourceWithMergePatch(String namespace, ResourceType resourceType, String resourceName, String jsonStr);
    public String updateResourceWithMergePatch(ResourceType resourceType, String resourceName, String jsonStr);

    public String updateResourceWithStrategicMergePatch(String namespace, ResourceType resourceType, String resourceName, String jsonStr);
    public String updateResourceWithStrategicMergePatch(ResourceType resourceType, String resourceName, String jsonStr);

    public void close();
}
