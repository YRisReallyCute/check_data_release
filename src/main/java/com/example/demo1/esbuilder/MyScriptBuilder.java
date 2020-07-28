package com.example.demo1.esbuilder;

import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.sort.ScriptSortBuilder;

import java.util.HashMap;

public class MyScriptBuilder {

    private ScriptType scriptType;

    private InlineCodeBuilder inlineCode;

    private String lang;

    private HashMap<String, Object> params;

    public MyScriptBuilder() {

    }

//    public MyScriptBuilder withParams(HashMap<String, Object> params) {
//        this.params = params;
//        return this;
//    }

    public MyScriptBuilder withScriptType(ScriptType scriptType){
        this.scriptType = scriptType;
        return this;
    }

    public MyScriptBuilder withLang(String lang){
        this.lang = lang;
        return this;
    }

    public MyScriptBuilder withScriptCode(InlineCodeBuilder inlineCode){
        this.inlineCode = inlineCode;
        return this;
    }

    public Script build() {
        return new Script(scriptType, lang, inlineCode.toString(), inlineCode.params());
    }
}
