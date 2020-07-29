package com.example.demo1.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighlightUtil {

    public static String combine(String highlightField1, String highlightField2, String preTag, String postTag) throws Exception {
        List<Integer> array1 = toArray(highlightField1, preTag, postTag);
        List<Integer> array2 = toArray(highlightField2, preTag, postTag);
        List<Integer> conbinedArray = combineArray(array1, array2);
        return arrayToString(highlightField1.replace(preTag, "").replace(postTag, ""), conbinedArray, preTag, postTag);
    }

    private static List<Integer> toArray(String highlightField, String preTag, String postTag){

        String[] fields = highlightField.split(preTag);
        int len = 0;
        List<Integer> array = new ArrayList<>();
        List<String> fieldList = new ArrayList<>();
        for (String field : fields) {
            int begin = 0;
            if(field.contains(postTag))
                begin = 1;
            String[] fs = field.split(postTag);
            for( int i = len; i < len + fs[0].length(); i++)
                array.add(begin);
            if (fs.length > 1){
                for(int i = len; i < len + fs[1].length(); i++)
                    array.add(0);
            }
        }

        return array;
    }

    private static String arrayToString(String originField, List<Integer> array, String preTag, String postTag){

        StringBuilder stringBuilder = new StringBuilder();
        int flag = 1;
        for (int i = 0; i < array.size(); i++) {
            if(flag == 1) {
                if (array.get(i) == 1) {
                    stringBuilder.append(preTag);
                    stringBuilder.append(originField.charAt(i));
                    flag = 0;
                }else{
                    stringBuilder.append(originField.charAt(i));
                }
            }else{
                if(array.get(i) == 1){
                    stringBuilder.append(originField.charAt(i));
                }else{
                    stringBuilder.append(postTag);
                    stringBuilder.append(originField.charAt(i));
                    flag = 1;
                }
            }
        }

        return stringBuilder.toString();
    }

    private static List<Integer> combineArray(List<Integer> array1, List<Integer> array2) throws Exception {
        if(array1.size() != array2.size()) throw new Exception("输入参数有问题");
        List<Integer> res = new ArrayList<>();
        for (int i = 0 ; i < array1.size(); i++) {
            if(array1.get(i) == 1 || array2.get(i) == 1)
                res.add(1);
            else
                res.add(0);
        }
        return res;
    }
}
