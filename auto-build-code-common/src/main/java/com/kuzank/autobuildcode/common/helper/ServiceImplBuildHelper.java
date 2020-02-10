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
public class ServiceImplBuildHelper extends AbstractAutoBuildCodeHelper implements IAutoBuildCodeHelper {

    public ServiceImplBuildHelper(AutoBuildCodeConfigBean configBean) {
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
        return form.getFormName() + configBean.getClassNameSuffix() + "ServiceImpl.java";
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
                "import com.github.pagehelper.PageHelper;\n" +
                "import com.github.pagehelper.PageInfo;\n" +
                "import " + configBean.getBuildPackage() + "." + form.getFormName() + configBean.getClassNameSuffix() + ";\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.stereotype.Service;\n" +
                "import org.springframework.transaction.annotation.Isolation;\n" +
                "import org.springframework.transaction.annotation.Propagation;\n" +
                "import org.springframework.transaction.annotation.Transactional;\n\n" +
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
        // autowired
        classContentBuilder.append(buildClassAutoWired(form, configBean)).append("\n\n");
        // method
        classContentBuilder.append(buildClassMethod(form, configBean)).append("\n");
        // }
        classContentBuilder.append("}");

        return classContentBuilder;
    }

    private static StringBuilder buildClassHeader(Form form, AutoBuildCodeConfigBean configBean) {

        return new StringBuilder("" +
                "@Service\n" +
                "public class " + form.getFormName() + configBean.getClassNameSuffix() + "ServiceImpl implements " + form.getFormName() + configBean.getClassNameSuffix() + "Service");

    }

    private static StringBuilder buildClassAutoWired(Form form, AutoBuildCodeConfigBean configBean) {

        return new StringBuilder("" +
                "    @Autowired\n" +
                "    private " + form.getFormName() + configBean.getClassNameSuffix() + "Dao " + getLowerCaseFormClassName(form.getFormName() + configBean.getClassNameSuffix()) + "Dao;");
    }

    private static StringBuilder buildClassMethod(Form form, AutoBuildCodeConfigBean configBean) {

        StringBuilder methodBuilder = new StringBuilder();

        String POName = form.getFormName() + configBean.getClassNameSuffix();
        String PONameLowerCase = getLowerCaseFormClassName(POName);
        String DaoName = PONameLowerCase + "Dao";

        methodBuilder.append("" +
                "    @Override\n" +
                "    public int insert(" + POName + " " + PONameLowerCase + ") {\n" +
                "        return " + DaoName + ".insert(" + PONameLowerCase + ");\n" +
                "    }\n\n" +
                "    @Override\n" +
                "    public int insertSelective(" + POName + " " + PONameLowerCase + ") {\n" +
                "        return " + DaoName + ".insertSelective(" + PONameLowerCase + ");\n" +
                "    }\n\n" +
                "    /**\n" +
                "     * 事务注解说明：\n" +
                "     * propagation = Propagation.REQUIRED 支持当前事务，如果当前没有事务，就新建一个事务\n" +
                "     * isolation = Isolation.DEFAULT      使用数据库默认的事务隔离级别\n" +
                "     * timeout = 36000                    事务超时时间，单位毫秒\n" +
                "     * rollbackFor = Exception.class      回滚条件，程序抛出 Exception 级别的异常\n" +
                "     */\n" +
                "    @Override\n" +
                "    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)\n" +
                "    public int insertList(List<" + POName + "> " + PONameLowerCase + "s) {\n" +
                "        return " + DaoName + ".insertList(" + PONameLowerCase + "s);\n" +
                "    }\n\n"
        );

        if (form.isContainCommentField()) {
            methodBuilder.append("" +
                    "    @Override\n" +
                    "    public " + POName + " selectById(Integer id) {\n" +
                    "        return " + DaoName + ".selectById(id);\n" +
                    "    }\n\n" +
                    "    @Override\n" +
                    "    public " + POName + " selectByUUID(String uuid) {\n" +
                    "        return " + DaoName + ".selectByUUID(uuid);\n" +
                    "    }\n\n" +
                    "    @Override\n" +
                    "    public int deleteById(Integer id) {\n" +
                    "        return " + DaoName + ".deleteById(id);\n" +
                    "    }\n\n" +
                    "    @Override\n" +
                    "    public int deleteByUUID(String uuid) {\n" +
                    "        return " + DaoName + ".deleteByUUID(uuid);\n" +
                    "    }\n\n" +
                    "    @Override\n" +
                    "    public int updateById(" + POName + " " + PONameLowerCase + ") {\n" +
                    "        return " + DaoName + ".updateById(" + PONameLowerCase + ");\n" +
                    "    }\n\n" +
                    "    @Override\n" +
                    "    public int updateByUUID(" + POName + " " + PONameLowerCase + ") {\n" +
                    "        return " + DaoName + ".updateByUUID(" + PONameLowerCase + ");\n" +
                    "    }\n\n"
            );
        }

        methodBuilder.append("" +
                "    @Override\n" +
                "    public PageInfo<" + POName + "> findAll(int pageNum, int pageSize) {\n\n" +
                "        PageHelper.startPage(pageNum, pageSize > 100 ? 100 : pageSize);\n\n" +
                "        List<" + POName + "> " + PONameLowerCase + "s = " + DaoName + ".findAll();\n\n" +
                "        return new PageInfo<" + POName + ">(" + PONameLowerCase + "s);\n" +
                "    }");

        return methodBuilder;
    }

    private static String getLowerCaseFormClassName(String formClassName) {

        formClassName = formClassName.substring(0, 1).toLowerCase() + formClassName.substring(1);
        return formClassName;
    }
}
