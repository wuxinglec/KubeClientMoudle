package com.chy.kubeclient;

/**
 * Created by chyzh on 2016/3/5.
 */
public interface OperatorInterface {
    public String getAllPodsInNS(String ns);
    public String getAllNSs();
    public String getAllRCsInNS(String ns);
    public String getAllSrvsInNS(String ns);
    public String createNS(String json);
    public String createRCInNS(String json, String ns);
    public String createSrvInNS(String json, String ns);
    public String createPodInNS(String json, String ns);
    public String getPodInfoByName(String podName, String ns);
    public String getRCInfoByName(String rcName, String ns);
    public String getSrvInfoByName(String srvName, String ns);
    public String getNodeInfoByName(String nodeName);
}
