package com.wqy.freemarkerlayout;

import freemarker.template.TemplateModel;

/**
 * 保存 block 的内容的model
 */
public class TemplateBlockModel implements TemplateModel {

    private StringBuilder body = new StringBuilder();

    public static String blockVarName(Object block)
    {
        var name = block.toString();
        return TemplateBlockModel.class.getName() + "#" + name;
    }

    public TemplateBlockModel(String body) {
        this.body.append(body);
    }

    public String getBody() {
        return body.toString();
    }

    public void append(String body)
    {
        this.body.append(body);
    }

    @Override
    public String toString() {
        return "TemplateBlockModel{" +
                "body='" + body + '\'' +
                '}';
    }

  }
