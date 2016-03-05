package com.chy.kubeclient;

/**
 * Created by chyzh on 2016/3/5.
 */
public class Operator implements OperatorInterface {
    private ClientInterface _client;
    public Operator(String serveUrl) {
        this._client = new JerseyClient(serveUrl);
    }

    public Operator() {
        this._client = new JerseyClient("http://zhaochenyang.space:8080/api/v1");
    }

    public String getAllPodsInNS(String ns) {
        Params params = new Params();
        params.setNamespace(ns);
        params.setResourceType(ResourceType.PODS);
        return _client.list(params);
    }

    public String getAllNSs() {
        Params params = new Params();
        params.setResourceType(ResourceType.NAMESPACES);
        return _client.list(params);
    }

    public String getAllRCsInNS(String ns) {
        Params params = new Params();
        params.setResourceType(ResourceType.REPLICATIONCONTROLLERS);
        params.setNamespace(ns);
        return _client.list(params);
    }

    public String getAllSrvsInNS(String ns) {
        Params params = new Params();
        params.setNamespace(ns);
        params.setResourceType(ResourceType.SERVICES);
        return _client.list(params);
    }

    public String createNS(String json) {
        Params params = new Params();
        params.setResourceType(ResourceType.NAMESPACES);
        params.setJson(json);
        return _client.create(params);
    }

    public String createRCInNS(String json, String ns) {
        Params params = new Params();
        params.setNamespace(ns);
        params.setResourceType(ResourceType.REPLICATIONCONTROLLERS);
        params.setJson(json);
        return _client.create(params);
    }

    public String createSrvInNS(String json, String ns) {
        Params params = new Params();
        params.setNamespace(ns);
        params.setResourceType(ResourceType.SERVICES);
        params.setJson(json);
        return _client.create(params);
    }

    public String createPodInNS(String json, String ns) {
        Params params = new Params();
        params.setNamespace(ns);
        params.setResourceType(ResourceType.PODS);
        params.setJson(json);
        return _client.create(params);
    }

    public String getPodInfoByName(String podName, String ns) {
        Params params = new Params();
        params.setNamespace(ns);
        params.setResourceType(ResourceType.PODS);
        params.setName(podName);
        return _client.get(params);
    }

    public String getRCInfoByName(String rcName, String ns) {
        Params params = new Params();
        params.setNamespace(ns);
        params.setResourceType(ResourceType.REPLICATIONCONTROLLERS);
        params.setName(rcName);
        return _client.get(params);
    }

    public String getSrvInfoByName(String srvName, String ns) {
        Params params = new Params();
        params.setNamespace(ns);
        params.setResourceType(ResourceType.SERVICES);
        params.setName(srvName);
        return _client.get(params);
    }

    public String getNodeInfoByName(String nodeName) {
        Params params = new Params();
        params.setResourceType(ResourceType.NODES);
        params.setName(nodeName);
        return _client.get(params);
    }


}
