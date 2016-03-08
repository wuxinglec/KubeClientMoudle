package com.chy.yamlparser;

/**
 * Created by chyzh on 2016/3/7.
 */
public class TestYamlParser {
    public static void main(String[] args) {
        Yaml yaml = new Yaml("resources/redis-master-controller.yaml");
        String result = yaml.getJson();
        System.out.println(result);
    }
}
