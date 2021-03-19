package com.wqy.freemarkerlayout.test;

import com.wqy.freemarkerlayout.LayoutRegister;
import freemarker.template.Configuration;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.PrintWriter;
import java.util.HashMap;

public class TestOne extends TestCase {
    @Test
    public void testOne() throws Exception
    {
        var c = new Configuration(Configuration.VERSION_2_3_31);
        LayoutRegister.register(c);

        c.setClassLoaderForTemplateLoading(TestOne.class.getClassLoader(), "templates");

        var t = c.getTemplate("show.flt");

        var m = new HashMap<>();
        m.put("name", "Hello");

        var out = new PrintWriter(System.out);

        t.process(m, out);
    }
}
