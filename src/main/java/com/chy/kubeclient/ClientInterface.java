package com.chy.kubeclient;

import java.security.PublicKey;

/**
 * Created by chyzh on 2016/3/5.
 */
public interface ClientInterface {
    public String get(Params params);
    public String list(Params params);
    public String create(Params params);
    public String delete(Params params);
    public String update(Params params);
    public String updateWithMediaType(Params params, String mediaType);
    public String replace(Params params);
    public String options(Params params);
    public String head(Params params);
    public void close();
}
