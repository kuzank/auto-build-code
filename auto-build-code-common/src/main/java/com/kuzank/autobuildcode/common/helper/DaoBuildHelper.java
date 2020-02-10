package com.kuzank.autobuildcode.common.helper;

import com.kuzank.autobuildcode.common.AbstractAutoBuildCodeHelper;
import com.kuzank.autobuildcode.common.AutoBuildCodeConfigBean;
import com.kuzank.autobuildcode.common.AutoBuildCodeFile;
import com.kuzank.autobuildcode.common.IAutoBuildCodeHelper;
import com.kuzank.autobuildcode.common.beandefinition.Form;

import java.time.LocalDateTime;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/9/16
 */
public class DaoBuildHelper extends AbstractAutoBuildCodeHelper implements IAutoBuildCodeHelper {

    public DaoBuildHelper(AutoBuildCodeConfigBean configBean) {
        super(configBean);
    }

    @Override
    public AutoBuildCodeFile build(Form form) {

        return new AutoBuildCodeFile()
                .setFileContent(buildCode(form, this.getConfigBean()).toString())
                .setFileName(getFileName(form, this.getConfigBean()));
    }


    @Override
    public String getFileName(Form form, AutoBuildCodeConfigBean configBean) {

        return form.getFormName() + configBean.getClassNameSuffix() + "Dao.java";
    }


    private static StringBuilder buildCode(Form form, AutoBuildCodeConfigBean configBean) {

        StringBuilder codeBuilder = new StringBuilder();

        codeBuilder.append(buildPackage(configBean)).append("\n\n");
        codeBuilder.append(buildImport(form, configBean)).append("\n\n");
        codeBuilder.append(buildClassComment(configBean)).append("\n");
        codeBuilder.append(buildClassContent(form, configBean));

        return codeBuilder;
    }


    private static String buildPackage(AutoBuildCodeConfigBean configBean) {

        return "package " + configBean.getBuildPackage() + ";";
    }

    private static StringBuilder buildImport(Form form, AutoBuildCodeConfigBean configBean) {

        StringBuilder importBuilder = new StringBuilder("" +
                "import " + configBean.getBuildPackage() + "." + form.getFormName() + "" + configBean.getClassNameSuffix() + ";\n" +
                "import org.apache.ibatis.annotations.Mapper;\n" +
                "import org.apache.ibatis.annotations.Param;\n\n" +
                "import java.util.List;");

        return importBuilder;
    }

    private static String buildClassComment(AutoBuildCodeConfigBean configBean) {
        return "" +
                "/**\n" +
                " * <p> @author " + configBean.getAuthorName() + "\n" +
                " * <p> @date   " + LocalDateTime.now() + "\n" +
                " */";
    }

    private static StringBuilder buildClassContent(Form form, AutoBuildCodeConfigBean configBean) {

        StringBuilder classContentBuilder = new StringBuilder();

        // ClassHeader
        classContentBuilder.append(buildClassHeader(form, configBean));
        // {
        classContentBuilder.append(" {").append("\n\n");
        // method
        classContentBuilder.append(buildClassMethod(form, configBean));
        // }
        classContentBuilder.append("}");

        return classContentBuilder;
    }

    private static StringBuilder buildClassHeader(Form form, AutoBuildCodeConfigBean configBean) {

        return new StringBuilder("" +
                "@Mapper\n" +
                "public interface " + form.getFormName() + configBean.getClassNameSuffix() + "Dao");
    }

    private static StringBuilder buildClassMethod(Form form, AutoBuildCodeConfigBean configBean) {

        StringBuilder methodBuilder = new StringBuilder();

        String POName = form.getFormName() + configBean.getClassNameSuffix();
        String PONameLowerCase = getLowerCaseFormClassName(POName);

        methodBuilder.append("" +
                "    int insert(@Param(\"" + PONameLowerCase + "\") " + POName + " " + PONameLowerCase + ");\n\n" +
                "    int insertSelective(@Param(\"" + PONameLowerCase + "\") " + POName + " " + PONameLowerCase + ");\n\n" +
                "    int insertList(@Param(\"" + PONameLowerCase + "\") List<" + POName + "> " + PONameLowerCase + "s);\n\n"
        );

        if (form.isContainCommentField()) {
            methodBuilder.append("" +
                    "    " + POName + " selectById(@Param(\"id\") Integer id);\n\n" +
                    "    " + POName + " selectByUUID(@Param(\"uuid\") String uuid);\n\n" +
                    "    int deleteById(@Param(\"id\") Integer id);\n\n" +
                    "    int deleteByUUID(@Param(\"uuid\") String uuid);\n\n" +
                    "    int updateById(@Param(\"studentPO\") " + POName + " " + PONameLowerCase + ");\n\n" +
                    "    int updateByUUID(@Param(\"studentPO\") " + POName + " " + PONameLowerCase + ");\n\n"
            );
        }

        methodBuilder.append("    List<" + POName + "> findAll();\n");

        return methodBuilder;
    }

    private static String getLowerCaseFormClassName(String formClassName) {

        formClassName = formClassName.substring(0, 1).toLowerCase() + formClassName.substring(1);
        return formClassName;
    }
}
