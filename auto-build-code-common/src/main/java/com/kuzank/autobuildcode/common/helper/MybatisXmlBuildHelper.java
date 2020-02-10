package com.kuzank.autobuildcode.common.helper;

import com.kuzank.autobuildcode.common.AbstractAutoBuildCodeHelper;
import com.kuzank.autobuildcode.common.AutoBuildCodeConfigBean;
import com.kuzank.autobuildcode.common.AutoBuildCodeFile;
import com.kuzank.autobuildcode.common.IAutoBuildCodeHelper;
import com.kuzank.autobuildcode.common.beandefinition.Form;
import com.kuzank.autobuildcode.common.beandefinition.FormField;
import com.kuzank.autobuildcode.common.util.StringUtil;

import java.util.List;

/**
 * <p>Description: </p>
 * TODO queryByParam
 *
 * @author kuzank  2018/9/16
 */
public class MybatisXmlBuildHelper extends AbstractAutoBuildCodeHelper implements IAutoBuildCodeHelper {

    public MybatisXmlBuildHelper(AutoBuildCodeConfigBean configBean) {
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
        return form.getFormName() + configBean.getClassNameSuffix() + "Dao.xml";
    }


    private static StringBuilder buildCode(Form form, AutoBuildCodeConfigBean configBean) {

        StringBuilder codeBuilder = new StringBuilder("" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\n" +
                "<mapper namespace=\"" + configBean.getBuildPackage() + "." + form.getFormName() + configBean.getClassNameSuffix() + "Dao\">\n\n");

        codeBuilder.append(buildBaseResultMap(form, configBean)).append("\n\n");
        codeBuilder.append(buildSqlBaseColumn(form)).append("\n\n");
        codeBuilder.append(buildInsear(form, configBean)).append("\n\n");
        codeBuilder.append(buildInsearSelective(form, configBean)).append("\n\n");
        codeBuilder.append(buildInsertList(form, configBean)).append("\n\n");

        if (form.isContainCommentField()) {
            codeBuilder.append(buildSelectById(form)).append("\n\n");
            codeBuilder.append(buildSelectByUUID(form)).append("\n\n");
            codeBuilder.append(buildDeleteById(form)).append("\n\n");
            codeBuilder.append(buildDeleteByUUID(form)).append("\n\n");
            codeBuilder.append(buildUpdateById(form, configBean)).append("\n\n");
            codeBuilder.append(buildUpdateByUUID(form, configBean)).append("\n\n");
        }

        codeBuilder.append(buildFindAll(form)).append("\n\n");
        codeBuilder.append("</mapper>");

        return codeBuilder;
    }

    private static StringBuilder buildBaseResultMap(Form form, AutoBuildCodeConfigBean configBean) {

        StringBuilder builder = new StringBuilder();


        builder.append("    <resultMap id=\"BaseResultMap\" type=\"" + configBean.getBuildPackage() + "." + form.getFormName() + configBean.getClassNameSuffix() + "\">\n");

        List<FormField> formFieldList = form.getFormField();
        if (formFieldList != null) {
            for (FormField item : formFieldList) {
                builder.append("        <result column=\"" + item.getColumn() + "\" property=\"" + item.getField() + "\" jdbcType=\"" + item.getFieldType().getJdbcType() + "\"/>\n");
            }
        }

        builder.append("    </resultMap>");
        return builder;
    }

    private static StringBuilder buildSqlBaseColumn(Form form) {

        StringBuilder builder = new StringBuilder("    <sql id=\"Base_Column_List\">\n");

        List<FormField> formFieldList = form.getFormField();

        StringBuilder tempBuilder = new StringBuilder();
        if (formFieldList != null) {
            for (FormField item : formFieldList) {
                if (tempBuilder.length() > 0) {
                    tempBuilder.append(",\n");
                }
                tempBuilder.append("        " + item.getColumn());
            }
        }

        builder.append(tempBuilder);
        builder.append("\n    </sql>");
        return builder;
    }

    private static StringBuilder buildInsear(Form form, AutoBuildCodeConfigBean configBean) {

        StringBuilder builder = new StringBuilder("    <insert id=\"insert\"");

        StringBuilder valueBuilder = new StringBuilder();

        List<FormField> formFieldList = form.getFormField();

        if (formFieldList != null) {
            String formClassName = getLowerCaseFormClassName(form, configBean);
            for (FormField item : formFieldList) {
                if (item.isPrimary()) {
                    builder.append(" useGeneratedKeys=\"true\" keyProperty=\"" + formClassName + "." + item.getField() + "\"");
                    break;
                }
            }
            for (FormField item : formFieldList) {
                if (valueBuilder.length() > 0) {
                    valueBuilder.append(",\n");
                }
                valueBuilder.append("            #{" + formClassName + "." + item.getField() + ",jdbcType=" + item.getFieldType().getJdbcType() + "}");
            }
        }

        builder.append(">\n");
        builder.append("        INSERT INTO " + form.getCollection() + " (\n");
        builder.append("        <include refid=\"Base_Column_List\"/>\n");
        builder.append("        ) VALUES (\n");
        builder.append(valueBuilder).append("\n");
        builder.append("        )\n");
        builder.append("    </insert>");

        return builder;
    }

    private static StringBuilder buildInsearSelective(Form form, AutoBuildCodeConfigBean configBean) {

        StringBuilder builder = new StringBuilder("    <insert id=\"insertSelective\"");

        StringBuilder keyBuilder = new StringBuilder();
        StringBuilder valueBuilder = new StringBuilder();

        List<FormField> formFieldList = form.getFormField();

        if (formFieldList != null) {
            String formClassName = getLowerCaseFormClassName(form, configBean);
            for (FormField item : formFieldList) {
                if (item.isPrimary()) {
                    builder.append(" useGeneratedKeys=\"true\" keyProperty=\"" + formClassName + "." + item.getField() + "\"");
                    break;
                }
            }
            for (FormField item : formFieldList) {
                keyBuilder.append("            <if test=\"" + formClassName + "." + item.getField() + " != null\">" + item.getColumn() + ",</if>\n");
                valueBuilder.append("            <if test=\"" + formClassName + "." + item.getField() + " != null\">#{" + formClassName + "." + item.getField() + ",jdbcType=" + item.getFieldType().getJdbcType() + "},</if>\n");
            }
        }

        builder.append(">\n");
        builder.append("        INSERT INTO " + form.getCollection() + "\n");
        builder.append("        <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n");
        builder.append(StringUtil.removeLastComma(keyBuilder));
        builder.append("        </trim>\n");
        builder.append("        VALUES\n");
        builder.append("        <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n");
        builder.append(StringUtil.removeLastComma(valueBuilder));
        builder.append("        </trim>\n");
        builder.append("    </insert>");

        return builder;
    }

    private static StringBuilder buildInsertList(Form form, AutoBuildCodeConfigBean configBean) {

        List<FormField> formFieldList = form.getFormField();

        StringBuilder builder = new StringBuilder("" +
                "    <insert id=\"insertList\">\n" +
                "        INSERT INTO book (\n" +
                "        <include refid=\"Base_Column_List\"/>\n" +
                "        )VALUES\n" +
                "        <foreach collection=\"bookPOs\" item=\"bookPO\" index=\"index\" separator=\",\">\n" +
                "            (\n");

        StringBuilder tempBuilder = new StringBuilder();

        if (formFieldList != null) {
            String formClassName = getLowerCaseFormClassName(form, configBean);
            for (FormField item : formFieldList) {
                if (tempBuilder.length() > 0) {
                    tempBuilder.append(",\n");
                }
                tempBuilder.append("            #{" + formClassName + "." + item.getField() + ",jdbcType=" + item.getFieldType().getJdbcType() + "}");
            }
        }

        builder.append(tempBuilder);
        builder.append("\n" +
                "            )\n" +
                "        </foreach>\n" +
                "    </insert>");

        return builder;
    }

    private static StringBuilder buildSelectById(Form form) {

        return new StringBuilder("" +
                "    <select id=\"selectById\" resultMap=\"BaseResultMap\" parameterMap=\"java.lang.Integer\">\n" +
                "        select * from " + form.getCollection() + " where id = #{id} and deleted = 0\n" +
                "    </select>");
    }

    private static StringBuilder buildSelectByUUID(Form form) {

        return new StringBuilder("" +
                "    <select id=\"selectByUUID\" resultMap=\"BaseResultMap\" parameterMap=\"java.lang.String\">\n" +
                "        select * from " + form.getCollection() + " where uuid = ${uuid} and deleted = 0\n" +
                "    </select>");
    }


    private static StringBuilder buildDeleteById(Form form) {

        return new StringBuilder("" +
                "    <update id=\"deleteById\" parameterMap=\"java.lang.Integer\">\n" +
                "        update " + form.getCollection() + " set deleted = 1,updatedBy = ${updatedBy},updatedAt=now() where id =#{id}\n" +
                "    </update>");
    }

    private static StringBuilder buildDeleteByUUID(Form form) {

        return new StringBuilder("" +
                "    <update id=\"deleteByUUID\" parameterMap=\"java.lang.String\">\n" +
                "        update " + form.getCollection() + " set deleted = 1,updatedBy = ${updatedBy},updatedAt=now() where uuid =#{uuid}\n" +
                "    </update>");
    }

    private static StringBuilder buildUpdateById(Form form, AutoBuildCodeConfigBean configBean) {

        StringBuilder builder = new StringBuilder();
        StringBuilder tempBuilder = new StringBuilder();

        List<FormField> formFieldList = form.getFormField();

        String formClassName = getLowerCaseFormClassName(form, configBean);

        if (formFieldList != null) {
            for (FormField item : formFieldList) {
                tempBuilder.append("            <if test=\"" + formClassName + "." + item.getField() + " != null\">" + item.getColumn() + "= #{" + formClassName + "." + item.getField() + ",jdbcType=" + item.getFieldType().getJdbcType() + "},</if>\n");
            }
        }

        builder.append("" +
                "    <update id=\"updateById\">\n" +
                "        UPDATE book\n" +
                "        <set>\n");
        builder.append(StringUtil.removeLastComma(tempBuilder));
        builder.append("" +
                "        </set>\n" +
                "        WHERE id = #{" + formClassName + ".id,jdbcType=INTEGER}\n" +
                "    </update>");

        return builder;
    }

    private static StringBuilder buildUpdateByUUID(Form form, AutoBuildCodeConfigBean configBean) {

        StringBuilder builder = new StringBuilder();
        StringBuilder tempBuilder = new StringBuilder();

        List<FormField> formFieldList = form.getFormField();

        String formClassName = getLowerCaseFormClassName(form, configBean);

        if (formFieldList != null) {
            for (FormField item : formFieldList) {
                tempBuilder.append("            <if test=\"" + formClassName + "." + item.getField() + " != null\">" + item.getColumn() + "= #{" + formClassName + "." + item.getField() + ",jdbcType=" + item.getFieldType().getJdbcType() + "},</if>\n");
            }
        }

        builder.append("" +
                "    <update id=\"updateByUUID\">\n" +
                "        UPDATE book\n" +
                "        <set>\n");
        builder.append(StringUtil.removeLastComma(tempBuilder));
        builder.append("" +
                "        </set>\n" +
                "        WHERE id = #{" + formClassName + ".uuid,jdbcType=VARCHAR}\n" +
                "    </update>");

        return builder;
    }

    private static StringBuilder buildFindAll(Form form) {

        return new StringBuilder("" +
                "    <select id=\"findAll\" resultMap=\"BaseResultMap\">\n" +
                "        select * from " + form.getCollection() + (form.isContainCommentField() ? " where deleted = 0\n" : "\n") +
                "    </select>"
        );
    }

    private static String getLowerCaseFormClassName(Form form, AutoBuildCodeConfigBean configBean) {

        String formClassName = form.getFormName();
        formClassName = formClassName.substring(0, 1).toLowerCase() + formClassName.substring(1) + configBean.getClassNameSuffix();
        return formClassName;
    }
}
