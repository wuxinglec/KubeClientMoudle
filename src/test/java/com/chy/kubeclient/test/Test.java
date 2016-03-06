package com.chy.kubeclient.test;

import com.chy.kubeclient.Operator;
import com.chy.kubeclient.OperatorInterface;
import com.chy.kubeclient.Utils;
import org.apache.log4j.BasicConfigurator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chyzh on 2016/3/5.
 */
public class Test {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        OperatorInterface opt =new Operator();
        Map<String, String> options = new HashMap<String, String>();
        options.put("tailLines", "20");
        options.put("follow", "true");
        opt.getLogByPodNameInNS("default", "redis-master-bp9rv", options);
        opt.close();
    }
}
