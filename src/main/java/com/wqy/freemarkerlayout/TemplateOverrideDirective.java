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
 * <@override block="block name"></@override>
 */
public class TemplateOverrideDirective implements TemplateDirectiveModel {

    /**
     * 块的内容是否追加到已存在的块中
     */
    private boolean append = false;

    public TemplateOverrideDirective() {
    }

    public TemplateOverrideDirective(boolean append) {
        this.append = append;
    }

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        // 把此指令的内容先保存下来
        var out = new StringWriter();
        if (body != null) {
            body.render(out);
        }

        var blockName = params.get("block").toString();
        var varName = TemplateBlockModel.blockVarName(blockName);

        TemplateBlockModel blockModel;
        if (append && null != (blockModel = (TemplateBlockModel) env.getVariable(varName))) {
            blockModel.append(out.toString());
        }
        else {
            blockModel = new TemplateBlockModel(out.toString());
        }

        env.setVariable(varName, blockModel);
    }
}
