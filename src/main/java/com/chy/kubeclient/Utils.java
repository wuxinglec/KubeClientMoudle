package com.chy.kubeclient;

import java.io.*;

/**
 * Created by chyzh on 2016/3/5.
 */
public class Utils {
    public static String getJsonFromJsonFile(String jsonPath) {
        StringBuilder result = new StringBuilder();
        try {
            InputStream is = new FileInputStream(jsonPath);
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            line = reader.readLine();
            while (line != null) {
                result.append(line);
                line = reader.readLine();
            }
            reader.close();
            is.close();
        }catch(IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}
