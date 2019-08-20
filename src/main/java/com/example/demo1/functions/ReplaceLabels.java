package com.example.demo1.functions;

import com.example.demo1.model.*;
import org.springframework.stereotype.Component;

import javax.jws.soap.SOAPBinding;
import java.util.Optional;

@Component
public class ReplaceLabels {

    private static String str1_0="<span class=\"title_1\">";

    private static String str1_1="</span>";

    private static String str2_0="<span class=\"title_2\">";

    private static String str2_1="</span>";

    public String getStr1_0() {
        return str1_0;
    }

    public String getStr1_1() {
        return str1_1;
    }

    public String getStr2_0() {
        return str2_0;
    }

    public String getStr2_1() {
        return str2_1;
    }

}
