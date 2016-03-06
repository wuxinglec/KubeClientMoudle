package com.chy.kubeclient.test;

import com.chy.kubeclient.OperatorV2;
import com.chy.kubeclient.ResourceType;
import org.apache.log4j.BasicConfigurator;

/**
 * Created by chyzh on 2016/3/6.
 */
public class TestGetResourceList {
    public static void main(String[] args) {
        BasicConfigurator.configure();

        OperatorV2 op = new OperatorV2();
        op.getResourceList(ResourceType.PODS);
        op.close();
    }
}
