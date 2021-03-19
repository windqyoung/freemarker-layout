package com.wqy.freemarkerlayout;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/**
 * <@extends name="layout name"></@extends>
 */
public class TemplateExtendsDirective implements TemplateDirectiveModel {

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {

        // 此标签的body内容忽略
        // 需要的是处理 override， append 的数据
        if (body != null) {
            var bodyOut = new StringWriter();
            body.render(bodyOut);
        }

        var name = params.get("name");
        if (name == null) {
            throw new TemplateException("继承未设置父模板", env);
        }
        var ftlName = name.toString();
        env.include(ftlName, env.getOutputEncoding(), true);
    }
}
