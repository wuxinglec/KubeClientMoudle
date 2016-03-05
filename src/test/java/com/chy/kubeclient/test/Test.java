package com.chy.kubeclient.test;

import com.chy.kubeclient.Operator;
import com.chy.kubeclient.OperatorInterface;
import com.chy.kubeclient.Utils;
import org.apache.log4j.BasicConfigurator;

/**
 * Created by chyzh on 2016/3/5.
 */
public class Test {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        OperatorInterface opt =new Operator();
        //System.out.println(opt.getNodeInfoByName("127.0.0.1"));
        opt.createPodInNS(Utils.getJsonFromJsonFile("resources/newPod.json"), "default");
    }
}
