package com.chy.kubeclient.test;

import com.chy.kubeclient.OperatorV2;
import com.chy.kubeclient.ResourceType;
import com.chy.kubeclient.Utils;
import org.apache.log4j.BasicConfigurator;

/**
 * Created by chyzh on 2016/3/6.
 */
public class TestUpdateResource {
    public static void main(String[] args) {
        BasicConfigurator.configure();

        OperatorV2 op = new OperatorV2();

        //op.createResource("ns-test", ResourceType.PODS, Utils.getJsonFromJsonFile("resources/newPod.json"));
        /*
        op.updateResourceWithMergePatch("ns-test", ResourceType.PODS, "my-demo-pod"
                , Utils.getJsonFromJsonFile("resources/mergeUpdate.json"));
*/
        op.updateResourceWithStrategicMergePatch(ResourceType.PODS, "frontend-mlosh"
                , Utils.getJsonFromJsonFile("resources/strategicMergeUpdate.json"));

        op.close();
    }
}
