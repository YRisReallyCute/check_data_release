package com.example.demo1.esbuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InlineCodeBuilder {

    /**
     * inline code 初始
     */
    private String begin = "int total=0;";

    /**
     * inline code 结束
     */
    private String end = "return total;";

    /**
     * code 主体
     */
    private final List<InlineCode> bodyClause = new ArrayList();

    public InlineCodeBuilder addBody(InlineCode s){
        this.bodyClause.add(s);
        return this;
    }

    public InlineCodeBuilder codeBegin(String s){
        this.begin = s;
        return this;
    }

    public InlineCodeBuilder codeEnd(String s){
        this.end = s;
        return this;
    }

    /**
     * 生成params
     * @return HashMap
     */
    public HashMap<String, Object> params() {
        HashMap<String, Object> map = new HashMap<>();
        for (InlineCode inlineCode : bodyClause) {
            HashMap<String, Object> tmp = inlineCode.param();
            for (String s : tmp.keySet()) {
                map.put(s, tmp.get(s));
            }
        }
        return map;
    }

    public String toString() {

        StringBuilder code = new StringBuilder();
        code.append(begin);
        for (InlineCode inlineCode : bodyClause) {
            code.append(inlineCode.build());
        }
        code.append(end);

        return code.toString();
    }
}
