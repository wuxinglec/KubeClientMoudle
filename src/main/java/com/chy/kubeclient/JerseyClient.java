package com.chy.kubeclient;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.ws.rs.core.MediaType;

/**
 * Created by chyzh on 2016/3/5.
 */
public class JerseyClient  implements ClientInterface {
    private static final Logger LOG = LogManager.getLogger(JerseyClient.class.getName());
    private static final String METHOD_PATCH = "PATCH";
    private String _baseUrl = null;
    Client _client = null;

    public JerseyClient(String baseUrl) {
        DefaultClientConfig config = new DefaultClientConfig();
        config.getProperties().put(URLConnectionClientHandler
                .PROPERTY_HTTP_URL_CONNECTION_SET_METHOD_WORKAROUND, true);
        this._client = Client.create(config);
        this._baseUrl = baseUrl;
    }

    public String get(Params params) {
        WebResource resource = _client.resource(_baseUrl + params.buildPath());
        LOG.info("URL: " + _baseUrl + params.buildPath());
        String response = resource.accept(MediaType.APPLICATION_JSON_TYPE).get(String.class);
        LOG.info("Get one resource:\n" + response);
        return response;
    }

    public String list(Params params) {
        WebResource resource = _client.resource(_baseUrl + params.buildPath());
        LOG.info("URL: " + _baseUrl + params.buildPath());
        String response = resource.accept(MediaType.APPLICATION_JSON_TYPE).get(String.class);
        return response;
    }

    public String create(Params params) {
        WebResource resource = _client.resource(_baseUrl + params.buildPath());
        LOG.info("URL: " + _baseUrl + params.buildPath());
        LOG.info("Create resource:" + params.getJson());
        String response = (null == params.getJson()) ? resource.accept(MediaType.APPLICATION_JSON)
                .post(String.class) : resource.type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).post(String.class, params.getJson());
        return response;
    }

    public String delete(Params params) {
        WebResource resource = _client.resource(_baseUrl + params.buildPath());
        String response = resource.accept(MediaType.APPLICATION_JSON_TYPE).delete(String.class);
        LOG.info("Delete resource " + params.getResourceType().getType()
                + "/" + params.getName() + "result:\n" + response);
        return response;
    }

    public String update(Params params) {
        return updateWithMediaType(params, MediaType.APPLICATION_JSON);
    }

    public String updateWithMediaType(Params params, String mediaType) {
        WebResource resource = _client.resource(_baseUrl + params.buildPath());
        LOG.info("URL: " + _baseUrl + params.buildPath());
        LOG.info("Patch resource:" + params.getJson());
        String response = resource.type(mediaType).accept(MediaType.APPLICATION_JSON_TYPE)
                .method(METHOD_PATCH, String.class, params.getJson());
        LOG.info("Update resource:" + params.buildPath() + "result:\n" + response);
        return response;
    }

    public String replace(Params params) {
        WebResource resource = _client.resource(_baseUrl + params.buildPath());
        LOG.info("URL: " + _baseUrl + params.buildPath());
        LOG.info("Replace resource:" + params.getJson());
        String response = resource.type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE).put(String.class, params.getJson());
        LOG.info("Replace Resource " + params.buildPath() + "result:\n" + response);
        return response;
    }

    public String options(Params params) {
        WebResource resource = _client.resource(_baseUrl + params.buildPath());
        String response = resource.type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.TEXT_PLAIN_TYPE).options(String.class);
        LOG.info("Get options for resource " + params.getResourceType().getType()
                + "/" + params.getName() + "result:\n" + response);
        return response;
    }

    public String head(Params params) {
        WebResource resource = _client.resource(_baseUrl + params.buildPath());
        String response = resource.accept(MediaType.TEXT_PLAIN_TYPE).head().getResponseStatus().toString();
        LOG.info("Get head for resource " + params.getResourceType().getType()
                + "/" + params.getName() + "result:\n" + response);
        return response;
    }

    public void close() {
        _client.destroy();
    }
}
