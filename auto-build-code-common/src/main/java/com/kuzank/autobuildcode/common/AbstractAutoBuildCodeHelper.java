package com.kuzank.autobuildcode.common;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/9/17
 */
public abstract class AbstractAutoBuildCodeHelper implements IAutoBuildCodeHelper {

    public static final String SPACE = " ";

    private AutoBuildCodeConfigBean configBean = new AutoBuildCodeConfigBean();

    public AbstractAutoBuildCodeHelper(AutoBuildCodeConfigBean configBean) {
        this.configBean = configBean;
    }

    @Override
    public AutoBuildCodeConfigBean getConfigBean() {
        return this.configBean;
    }

}
