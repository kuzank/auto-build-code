package com.kuzank.autobuildcode.common.util;

import com.kuzank.autobuildcode.common.beandefinition.Form;
import com.kuzank.autobuildcode.common.exception.AutoBuildCodeException;
import com.kuzank.autobuildcode.common.helper.BuildHelper;
import org.junit.Test;

import java.io.IOException;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/9/28
 */
public class ClassUtilTest {

    public static final String clazzString = "" +
            "package com.kuzank.autobuildcode.common.util.test;\n" +
            "\n" +
            "import com.kuzank.autobuildcode.common.annotation.Form;\n" +
            "import com.kuzank.autobuildcode.common.annotation.FormField;\n" +
            "\n" +
            "@Form\n" +
            "public class JavaBean {\n" +
            "\n" +
            "    @FormField\n" +
            "    private String name;\n" +
            "}\n";

    @Test
    public void complier() throws IOException, ClassNotFoundException, AutoBuildCodeException {

        Class clazz = ClassUtil.compiler(
                "com.kuzank.autobuildcode.common.util.test.JavaBean",
                "JavaBean.java",
                clazzString);

        System.out.println(clazz);

        Form form = BuildHelper.Clazz2WebForm(clazz);

        System.out.println(form);
    }
}
