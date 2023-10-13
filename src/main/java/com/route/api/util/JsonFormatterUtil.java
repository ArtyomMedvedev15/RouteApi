package com.route.api.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonFormatterUtil {
    public static String jsonFormatter(String oldJson){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.fromJson(oldJson, Object.class).toString();
    }
}
