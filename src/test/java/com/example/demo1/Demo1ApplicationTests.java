package com.example.demo1;

import com.example.demo1.esbuilder.InlineCode;
import com.example.demo1.esbuilder.InlineCodeBuilder;
import com.example.demo1.esbuilder.MyScriptBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo1ApplicationTests {

    @Test
    public void contextLoads() {
    }
    @Test
    public void testBuilder() {
        Script myScript = new MyScriptBuilder()
                .withLang("painless")
                .withScriptType(ScriptType.INLINE)
                .withScriptCode(new InlineCodeBuilder()
                        .addBody(
                                new InlineCode().withKeyName("glbm")
                                        .withField("bzmc.keyword")
                                        .withSearchWords("头痛/脑热")
                                        .withRegix("[,;/]")
                                        .withWeight(5)
                        ).addBody(
                                new InlineCode().withKeyName("sx")
                                        .withField("sx.keyword")
                                        .withSearchWords("苔薄白")
                                        .withRegix("/")
                                        .withWeight(2)
                        ))
                .build();
        System.out.println(myScript.toString());
    }
}
