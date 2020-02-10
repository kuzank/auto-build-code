package com.kuzank.autobuildcode.common.helper;

import com.kuzank.autobuildcode.common.AbstractAutoBuildCodeHelper;
import com.kuzank.autobuildcode.common.AutoBuildCodeConfigBean;
import com.kuzank.autobuildcode.common.AutoBuildCodeFile;
import com.kuzank.autobuildcode.common.IAutoBuildCodeHelper;
import com.kuzank.autobuildcode.common.beandefinition.Form;
import com.kuzank.autobuildcode.common.beandefinition.FormField;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/9/16
 */
public class SqlBuildHelper extends AbstractAutoBuildCodeHelper implements IAutoBuildCodeHelper {

    public SqlBuildHelper(AutoBuildCodeConfigBean configBean) {
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
        return form.getFormName() + configBean.getClassNameSuffix() + ".sql";
    }

    private static StringBuilder buildCode(Form form, AutoBuildCodeConfigBean configBean) {

        StringBuilder codeBuilder = new StringBuilder();

        codeBuilder.append("" +
                "-- auto Generated on " + LocalDateTime.now() + " \n" +
                "-- DROP TABLE IF EXISTS " + form.getCollection() + "; \n" +
                "CREATE TABLE " + form.getCollection() + "(\n" +
                buildAllColumn(form) +
                ")ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '" + form.getCollection() + "';");

        return codeBuilder;
    }

    private static StringBuilder buildAllColumn(Form form) {

        StringBuilder builder = new StringBuilder();

        List<FormField> primaryKeys = new ArrayList<>();

        List<FormField> formFields = form.getFormField();
        if (formFields != null && formFields.size() > 0) {
            for (FormField item : formFields) {
                if (item.isPrimary()) {
                    primaryKeys.add(item);
                }
                builder.append(buildsingleColumn(item));
            }
        }

        StringBuilder primaryBuilder = new StringBuilder();

        for (FormField item : primaryKeys) {
            if (primaryBuilder.length() > 0) {
                primaryBuilder.append(",");
            }
            primaryBuilder.append("\tPRIMARY KEY (" + item.getField() + ")\n");
        }

        builder.append(primaryBuilder);

        return builder;
    }

    private static String buildsingleColumn(FormField formField) {

        String nullValue = "";
        String comment = "";

        if (formField.isCanBeNull()) {
            nullValue = "NULL";
        } else {
            nullValue = "NOT NULL";
        }

        if (formField.getComment().length() > 0) {
            comment = formField.getComment();
        } else {
            comment = formField.getField();
        }

        return "\t" + formField.getColumn() + " " + formField.getFieldType().getJdbcType() + " (" + formField.getFieldType().getLength() + ") " + nullValue + " DEFAULT '" + formField.getDefaultValue() + "' COMMENT '" + comment + "',\n";
    }
}
