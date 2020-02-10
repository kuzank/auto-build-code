package com.kuzank.autobuildcode.common;

import com.kuzank.autobuildcode.common.beandefinition.Form;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/9/18
 */
public interface IAutoBuildCodeHelper {

    AutoBuildCodeFile build(Form form);

    String getFileName(Form form, AutoBuildCodeConfigBean configBean);

    AutoBuildCodeConfigBean getConfigBean();
}
