package com.wqy.freemarkerlayout;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

import java.io.IOException;
import java.util.Map;

/**
 * 在<@override></@override>中使用， 替换父块中的值
 */
public class TemplateParentDirective implements TemplateDirectiveModel {
    public static String placeholder()
    {
        return TemplateParentDirective.class.getName() + "#placeholder##";
    }
    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        // 碰到此标签，输出占位符
        env.getOut().append(placeholder());
    }
}
