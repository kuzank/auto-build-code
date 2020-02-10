package com.kuzank.autobuildcode.common.builder;

import com.kuzank.autobuildcode.common.AutoBuildCodeConfigBean;
import com.kuzank.autobuildcode.common.AutoBuildCodeFile;
import com.kuzank.autobuildcode.common.beandefinition.Form;
import com.kuzank.autobuildcode.common.exception.AutoBuildCodeException;
import com.kuzank.autobuildcode.common.helper.*;
import com.kuzank.autobuildcode.common.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/9/16
 */
public class AutoCodeBuilder {

    private List<AutoBuildCodeFile> codeHelpers = new ArrayList<>();

    private AutoCodeBuilder() {
    }

    /**
     * 生成代码
     */
    public AutoCodeBuilder(Form targetForm, AutoBuildCodeConfigBean configBean) throws Exception {

        init(targetForm, configBean);
        buildFile(configBean);
    }

    public AutoCodeBuilder(Form targetForm) throws Exception {

        new AutoCodeBuilder(targetForm, new AutoBuildCodeConfigBean());
    }

    /**
     * 初始化代码自动生成的列表
     * 如果不想生成代码则注释
     */
    private void init(Form targetForm, AutoBuildCodeConfigBean configBean) {

        this.codeHelpers.add(new DaoBuildHelper(configBean).build(targetForm));
        this.codeHelpers.add(new JavaBeanBuildHelper(configBean).build(targetForm));
        this.codeHelpers.add(new MybatisXmlBuildHelper(configBean).build(targetForm));
        this.codeHelpers.add(new ServiceBuildHelper(configBean).build(targetForm));
        this.codeHelpers.add(new ServiceImplBuildHelper(configBean).build(targetForm));
        this.codeHelpers.add(new SqlBuildHelper(configBean).build(targetForm));
    }

    private void buildFile(AutoBuildCodeConfigBean configBean) throws Exception {

        if (codeHelpers == null || codeHelpers.size() == 0) {
            throw new AutoBuildCodeException("AutoCodeBuilder.List<AutoBuildCodeFile> require not null and not empty");
        }

        String parentPath = getBuildFilePath(configBean);

        for (AutoBuildCodeFile codeFile : codeHelpers) {
            FileUtil.writeToDisk(codeFile.getFileContent(), parentPath + codeFile.getFileName());
        }

    }

    private String getBuildFilePath(AutoBuildCodeConfigBean configBean) throws IOException {

        String projectDir = new File("").getCanonicalPath().replace("auto-build-code-common", "auto-build-code-datasource");
        if (!projectDir.endsWith(File.separator)) {
            projectDir = projectDir + File.separator;
        }
        projectDir = projectDir + "src" + File.separator + "main" + File.separator + "java";

        String autoCodeBuildPackage = configBean.getBuildPackage();

        if (autoCodeBuildPackage != null && autoCodeBuildPackage.length() > 0) {

            String[] packageList = autoCodeBuildPackage.split("\\.");
            for (String item : packageList) {
                if (!projectDir.endsWith(File.separator)) {
                    projectDir = projectDir + File.separator;
                }
                projectDir = projectDir + item;
            }
        }

        projectDir = projectDir.endsWith(File.separator) ? projectDir : projectDir + File.separator;

        return projectDir;
    }


}
