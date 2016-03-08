package com.chy.kubeclient.demo;

import com.chy.kubeclient.OperatorV2;
import com.chy.kubeclient.ResourceType;
import com.chy.kubeclient.Utils;
import com.chy.yamlparser.Yaml;
import org.apache.log4j.BasicConfigurator;

/**
 * Created by chyzh on 2016/3/8.
 */
public class DeployMessageBoardDemo {
    public static void main(String[] args){
        BasicConfigurator.configure();

        OperatorV2 op = new OperatorV2();
        op.createResource(ResourceType.REPLICATIONCONTROLLERS
                , Utils.getJsonFromJsonFile("resources/newrc.json"));

        /*
        op.createResource(ResourceType.SERVICES
        , new Yaml("resources/redis-master-service.yaml").getJson());
        op.createResource(ResourceType.REPLICATIONCONTROLLERS
                , new Yaml("resources/redis-slave-controller.yaml").getJson());
        op.createResource(ResourceType.SERVICES
                , new Yaml("resources/redis-slave-service.yaml").getJson());
        op.createResource(ResourceType.REPLICATIONCONTROLLERS
                , new Yaml("resources/frontend-controller.yaml").getJson());
        op.createResource(ResourceType.SERVICES
                , new Yaml("resources/frontend-service.yaml").getJson());

*/
        op.close();
    }
}
