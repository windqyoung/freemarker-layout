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
 * <@block name="block name"></@block>
 */
public class TemplateBlockDirective implements TemplateDirectiveModel {

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        var blockVar = TemplateBlockModel.blockVarName(params.get("name"));
        var varModel = env.getVariable(blockVar);
        // 有了块的数据
        if (varModel instanceof TemplateBlockModel) {
            processSubBlock(env, params, loopVars, body, (TemplateBlockModel) varModel);
            return;
        }

        // 没有子块
        if (body != null) {
            body.render(env.getOut());
        }
    }

    private void processSubBlock(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body, TemplateBlockModel blockModel) throws TemplateException, IOException {
        var blockBody = blockModel.getBody();

        // 修改父占位符
        if (blockBody.contains(TemplateParentDirective.placeholder()) && body != null) {
            var parentOut = new StringWriter();
            body.render(parentOut);
            blockBody = blockBody.replace(TemplateParentDirective.placeholder(), parentOut.toString());
        }

        env.getOut().append(blockBody);
    }
}