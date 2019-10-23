package com.example.demo1.util;

import java.util.List;

public enum StringUtil {
    ;
    public static String getListString(List<String> list){
        StringBuilder result = new StringBuilder();
        for(String s:list){
            result.append(s).append(" ");
        }
        return result.toString();
    }
}
