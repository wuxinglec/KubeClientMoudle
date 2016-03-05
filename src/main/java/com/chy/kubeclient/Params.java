package com.chy.kubeclient;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Created by chyzh on 2016/3/5.
 */
public class Params {
    private static final Logger LOG = LogManager.getLogger(Params.class.getName());

    private String namespace = null;
    private String name = null;
    private Map<String, String> fields = null;
    private Map<String, String> lables = null;
    private Map<String, String> notLabels = null;
    private Map<String, List<String>> inLabels = null;
    private Map<String, List<String>> notInLabels = null;
    private String json = null;
    private ResourceType resourceType = null;
    private String subPath = null;
    private boolean isVisitProxy = false;
    private boolean isSetWatcher = false;

    //返回访问api server的url
    public String buildPath() {
        StringBuilder result = (isVisitProxy ? new StringBuilder("/proxy")
                : (isSetWatcher ? new StringBuilder("/watch") : new StringBuilder("")));
        if (null != namespace)
            result.append("/namespaces/").append(namespace);
        result.append("/").append(resourceType.getType());

        if(null != name)
            result.append("/").append(name);

        if(null != subPath)
            result.append("/").append(subPath);

        if(null != lables && !lables.isEmpty()
                || null != notLabels && !notLabels.isEmpty()
                || null != inLabels && inLabels.size() > 0
                || null != notInLabels && notInLabels.size() > 0
                || null != fields && fields.size() > 0) {
            StringBuilder labelSelectorStr = null;
            StringBuilder fieldSelectorStr = null;
            try {
                labelSelectorStr = buildLabelSelector();
                fieldSelectorStr = buildFieldSelector();
            } catch (UnsupportedEncodingException e1) {
                LOG.error(e1);
            }
            if (labelSelectorStr.length() + fieldSelectorStr.length() > 0)
                result.append("?");
            if(labelSelectorStr.length() > 0) {
                result.append("labelSelector=").append(labelSelectorStr.toString());
                if (fieldSelectorStr.length() > 0)
                    result.append(",");
            }
            if(fieldSelectorStr.length() > 0) {
                result.append("fieldSelector=").append(fieldSelectorStr.toString());
            }
        }

        return result.toString();
    }

    private StringBuilder buildLabelSelector() throws UnsupportedEncodingException {
        StringBuilder  result = new StringBuilder();
        if (null != lables) {
            for (String key : lables.keySet()) {
                if (result.length() > 0) {
                    result.append(",");
                }
                result.append(URLEncoder.encode(key + "=" + lables.get(key), "GBK"));
            }
        }

        if (null != notLabels) {
            for(String key : notLabels.keySet()) {
                if(result.length() > 0){
                    result.append(",");
                }
                result.append(URLEncoder.encode(key + "!=" + notLabels.get(key), "GBK"));
            }
        }

        if (null != inLabels) {
            for (String key : inLabels.keySet()) {
                if (result.length() > 0) {
                    result.append(URLEncoder.encode("," , "GBK"));
                }
                result.append(URLEncoder.encode(key + " in ("
                        + listToString(inLabels.get(key), ",") + ")", "GBK"));
            }
        }

        if (null != notLabels) {
            for (String key : notInLabels.keySet()) {
                if (result.length() > 0) {
                    result.append(URLEncoder.encode(",", "GBK"));
                }
                result.append(URLEncoder.encode(key + " notin ("
                        + listToString(notInLabels.get(key), ",") + ")", "GBK"));
            }
        }
        LOG.info("label result:" + result);
        return result;
    }

    private StringBuilder buildFieldSelector() throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        if (null != fields) {
            for(String key : fields.keySet()) {
                if (result.length() > 0){
                    result.append(",");
                }
                result.append(URLEncoder.encode(key + "=" + fields.get(key), "GBK"));
            }
        }
        return result;
    }

    private String listToString(List<String> list, String delim) {
        boolean isFirst = true;
        StringBuilder result = new StringBuilder();
        for (String str : list) {
            if(isFirst) {
                result.append(str);
                isFirst = false;
            } else {
                result.append(delim).append(str);
            }
        }

        return result.toString();
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }

    public Map<String, String> getLables() {
        return lables;
    }

    public void setLables(Map<String, String> lables) {
        this.lables = lables;
    }

    public Map<String, String> getNotLabels() {
        return notLabels;
    }

    public void setNotLabels(Map<String, String> notLabels) {
        this.notLabels = notLabels;
    }

    public Map<String, List<String>> getInLabels() {
        return inLabels;
    }

    public void setInLabels(Map<String, List<String>> inLabels) {
        this.inLabels = inLabels;
    }

    public Map<String, List<String>> getNotInLabels() {
        return notInLabels;
    }

    public void setNotInLabels(Map<String, List<String>> notInLabels) {
        this.notInLabels = notInLabels;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public String getSubPath() {
        return subPath;
    }

    public void setSubPath(String subPath) {
        this.subPath = subPath;
    }

    public boolean isVisitProxy() {
        return isVisitProxy;
    }

    public void setVisitProxy(boolean visitProxy) {
        isVisitProxy = visitProxy;
    }

    public boolean isSetWatcher() {
        return isSetWatcher;
    }

    public void setSetWatcher(boolean setWatcher) {
        isSetWatcher = setWatcher;
    }

}
