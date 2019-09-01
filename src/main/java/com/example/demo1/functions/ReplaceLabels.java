package com.example.demo1.functions;

import com.example.demo1.model.*;
import org.springframework.stereotype.Component;

import javax.jws.soap.SOAPBinding;
import java.util.Optional;

@Component
public class ReplaceLabels {

    private static String str1_0="<span class='title_1'>";

    private static String str1_1="</span>";

    private static String str2_0="<span class='title_2'>";

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


    public String rep(ReplaceLabels replaceLabels, String strToReplace){
        String str1_0 = replaceLabels.getStr1_0();
        String str1_1 = replaceLabels.getStr1_1();
        String str2_0 = replaceLabels.getStr2_0();
        String str2_1 = replaceLabels.getStr2_1();

        return strToReplace
                .replaceAll("<(?!(h1>)|(/h1>)|(p>)|(br/>)|(br />))","小于") // 非标签的 < 更换为 小于
                .replaceAll("(?<!(<h1)|(</h1)|(<p)|(<br/)|(<br /))>","大于")  // 非标签的 > 更换为 大于
                .replaceAll("<h1>",str1_0)
                .replaceAll("</h1><br/>",str1_1)
                .replaceAll("<h2>",str2_0)
                .replaceAll("</h2><br/>",str2_1)
                .replaceAll("<!--.*-->", "");
    }

}
