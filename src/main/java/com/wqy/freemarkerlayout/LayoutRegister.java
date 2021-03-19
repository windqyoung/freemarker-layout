package com.wqy.freemarkerlayout;


import freemarker.template.Configuration;

public class LayoutRegister {

    public static void register(Configuration config)
    {
        config.setSharedVariable("extends", new TemplateExtendsDirective());
        config.setSharedVariable("block", new TemplateBlockDirective());
        config.setSharedVariable("override", new TemplateOverrideDirective());
        config.setSharedVariable("append", new TemplateOverrideDirective(true));
        config.setSharedVariable("parent", new TemplateParentDirective());


    }
}
