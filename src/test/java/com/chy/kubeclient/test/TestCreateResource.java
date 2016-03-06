package com.chy.kubeclient.test;

import com.chy.kubeclient.OperatorV2;
import com.chy.kubeclient.ResourceType;
import com.chy.kubeclient.Utils;
import org.apache.log4j.BasicConfigurator;

/**
 * Created by chyzh on 2016/3/6.
 */
public class TestCreateResource {
    public static void main(String[] args){
        BasicConfigurator.configure();

        OperatorV2 op = new OperatorV2();
        op.createResource("ns-test", ResourceType.PODS, Utils.getJsonFromJsonFile("resources/newPod.json"));

        op.close();
    }
}
