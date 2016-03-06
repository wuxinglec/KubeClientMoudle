package com.chy.kubeclient;

import java.util.Map;

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

    public String deleteNS(String nsName);
    public String deletePodInNS(String ns, String podName);
    public String deleteRCInNS(String ns, String rcName);
    public String deleteSrvInNS(String ns, String srvName);

    public String getLogByPodNameInNS(String ns, String podName, Map<String, String> options);

    public void close();
}
