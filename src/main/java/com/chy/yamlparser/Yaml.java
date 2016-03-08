package com.chy.yamlparser;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by chyzh on 2016/3/7.
 */
public class Yaml {
    private String filePath = null;
    private static int count = 0;

    public Yaml(String yamlFilePath) {
        this.filePath = yamlFilePath;
    }

    public String getJson() {
        ArrayList<String> strs = new ArrayList<String>();
        try {
            InputStream inputStream = new FileInputStream(filePath);
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            line = reader.readLine();
            while (line != null) {
                strs.add(line);
                line = reader.readLine();
            }
            reader.close();
            inputStream.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

        return "{" + parse(strs, 0, strs.size() - 1).toString() + "}";
    }

    private StringBuilder parse(ArrayList<String> strs, int start, int end) {//包括start和end
        StringBuilder result = new StringBuilder();
        boolean error = false;
        while(start <= end) {
            if(error){
                break;
            }
            String[] s = strs.get(start).split(":");
            String temp= null;
            if (start < end) {
                temp = strs.get(start + 1);
            }

            switch(type(strs.get(start), temp)) {
                case 1:
                    result.append("\"").append(s[0].trim()).append("\":\"").append(s[1].trim()).append("\"");
                    if(start < end) {
                        result.append(",");
                    }
                    start ++;
                    break;
                case 2:
                    int e2 = calEnd(strs, start);//计算结束位置
                    result.append("\"").append(s[0].trim()).append("\":").append("{")
                            .append(parse(strs, start+1, e2).toString()).append("}");
                    start = e2+1;
                    if(start < end) {
                        result.append(",");
                    }
                    break;
                case 3:
                    System.out.println("start=" + start);
                    int e3 = calEnd(strs, start);
                    System.out.println("e3=" + e3);
                    result.append("\"").append(s[0].trim()).append("\":")
                            .append("[").append(list(strs, start + 1, e3)).append("]");
                    start = e3 + 1;
                    if (start < end) {
                        result.append(",");
                    }
                    break;
                default:
                    error = true;
            }

        }
        return result;
    }

    private  int countSubStr(String str, String subStr) {
        if (str.indexOf(subStr)==-1)
        {
            return count;
        }
        else if(str.indexOf(subStr) != -1)
        {
            count ++;
            return countSubStr(str.substring(str.indexOf(subStr) + subStr.length()), subStr);
        }
        return 0;
    }

    //判断类型
    public int type(String s1, String s2) {
        if(null != s2 && "-".equals(s2.trim().substring(0, 1)))
            return 3;
        else {
            String s[] = s1.split(":");
            if(s.length < 2 || "".equals(s[1]) || s[1].trim().substring(0, 1) == "#")
                return 2;
        }
        return 1;
    }

    //计算范围
    private int calEnd(ArrayList<String> strings, int start){
        int end = strings.size() - 1;
        count = 0;
        int countTableStart = countSubStr(strings.get(start), " ");
        start ++;
        while(start <= end){
            count = 0;
            int tmp = countSubStr(strings.get(start), " ");
            if(tmp < countTableStart)
                break;
            else if (tmp == countTableStart){
                String tmpStr = strings.get(start).trim().substring(0, 1);
                if(!("-".equals(tmpStr)))
                    break;
            }
            start ++;
        }
        if(start == end){
            return end;
        } else {
            return start - 1;
        }
    }

    //处理列表
    private StringBuilder lista(ArrayList<String> strings, int start, int end) {
        StringBuilder result = new StringBuilder();
        int next = next(strings, start, end);
        strings.set(start, strings.get(start).replaceFirst("-", " "));
        if (next == -1) {
            result.append(parse(strings, start, end));
        } else {
            result.append(parse(strings, start, next - 1)).append(lista(strings, next, end));
        }
        return result;
    }

    private String list(ArrayList<String> strings, int start, int end) {
        //System.out.println("lista: " + lista(strings, start, end).toString());
        return "{" + lista(strings, start, end) + "}";
    }

    private int next(ArrayList<String> strings, int start, int end) {
        count = 0;
        int countTable = countSubStr(strings.get(start), " ");
        start ++;
        while(start <= end) {
            count = 0;
            if(countSubStr(strings.get(start), " ") == countTable
                    && "-".equals(strings.get(start).trim().substring(0, 1))){
                return start;
            }
            start ++;
        }
        return -1;
    }

}
