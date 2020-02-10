package com.kuzank.autobuildcode.commander.autobuildcode;

import com.kuzank.autobuildcode.common.AutoBuildCodeConfigBean;
import com.kuzank.autobuildcode.common.AutoBuildCodeFile;
import com.kuzank.autobuildcode.common.beandefinition.Form;
import com.kuzank.autobuildcode.common.exception.AutoBuildCodeException;
import com.kuzank.autobuildcode.common.helper.BuildHelper;
import com.kuzank.autobuildcode.common.helper.JavaBeanBuildHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/9/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaBeanBuildHelperTest {

    @Autowired
    public AutoBuildCodeConfigBean autoBuildCodeConfigBean;

    @Test
    public void buildTest() throws AutoBuildCodeException {

        Form targetForm = BuildHelper.Clazz2WebForm(Student.class);

        AutoBuildCodeFile autoBuildCodeFile = new JavaBeanBuildHelper(autoBuildCodeConfigBean).build(targetForm);

        System.out.println(autoBuildCodeFile);
    }


    @Test
    public void getFileName() throws AutoBuildCodeException {

        Form targetForm = BuildHelper.Clazz2WebForm(Student.class);

        System.out.println(new JavaBeanBuildHelper(autoBuildCodeConfigBean).getFileName(targetForm, autoBuildCodeConfigBean));
    }
}
