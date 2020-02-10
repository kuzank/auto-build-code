package com.kuzank.autobuildcode.common.autobuildcode;

import com.kuzank.autobuildcode.common.beandefinition.Form;
import com.kuzank.autobuildcode.common.builder.AutoCodeBuilder;
import com.kuzank.autobuildcode.common.helper.BuildHelper;
import com.kuzank.autobuildcode.common.javabean.SchoolStudent;
import org.junit.Test;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/9/16
 */
public class AutoCodeBuilderTest {

    @Test
    public void buildDefinitionFormObjectTest() throws Exception {

        Form targetForm = BuildHelper.Clazz2WebForm(SchoolStudent.class);

        new AutoCodeBuilder(targetForm);
    }

}
