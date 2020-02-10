package com.kuzank.autobuildcode.common.helper;

import com.kuzank.autobuildcode.common.*;
import com.kuzank.autobuildcode.common.beandefinition.Form;
import com.kuzank.autobuildcode.common.beandefinition.FormField;
import com.kuzank.autobuildcode.common.enumeration.FieldType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static com.kuzank.autobuildcode.common.util.StringUtil.notNull_Empty;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/9/16
 */
public class JavaBeanBuildHelper extends AbstractAutoBuildCodeHelper implements IAutoBuildCodeHelper {


    public JavaBeanBuildHelper(AutoBuildCodeConfigBean configBean) {
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
        return form.getFormName() + configBean.getClassNameSuffix() + ".java";
    }


    private static StringBuilder buildCode(Form form, AutoBuildCodeConfigBean configBean) {

        StringBuilder codeBuilder = new StringBuilder();

        // Package
        codeBuilder.append(buildPackage(configBean)).append("\n").append("\n");

        // Import
        codeBuilder.append(buildImport(form)).append("\n");

        // ClassComment
        codeBuilder.append(buildClassComment(form, configBean)).append("\n");

        // ClassContent
        codeBuilder.append(buildClassContent(form, configBean));

        return codeBuilder;
    }


    private static String buildPackage(AutoBuildCodeConfigBean configBean) {

        return "package " + configBean.getBuildPackage() + ";";
    }


    private static StringBuilder buildImport(Form form) {

        List<FormField> formFieldList = form.getFormField();

        // JavaBean 的基本属性不属于 java.lang.* 下,需要手动 import 的属性修饰类的路径
        List<String> needImportList = new ArrayList<String>() {{
            add(FieldType.DECIMAL.getJavaType());
            add(FieldType.DATETIME.getJavaType());
        }};

        // 使用 Set 集合避免重复
        Set<String> importSet = new LinkedHashSet<>();

        if (formFieldList != null && formFieldList.size() > 0) {
            for (FormField itemField : formFieldList) {

                if (needImportList.contains(itemField.getFieldType().getJavaType())) {
                    importSet.add(itemField.getFieldType().getJavaType());
                }
            }
        }

        StringBuilder importBuilder = new StringBuilder();

        // containCommentField
        if (form.isContainCommentField()) {
            importBuilder.append("import " + BaseEntity.class.getName() + ";\n\n");
        } else {
            importBuilder.append("import java.io.Serializable;\n");
        }

        for (String itemImport : importSet) {
            importBuilder.append("import" + " " + itemImport + ";" + "\n");
        }

        return importBuilder;
    }


    private static String buildClassComment(Form form, AutoBuildCodeConfigBean configBean) {
        return "" +
                "/**\n" +
                " * <p> Description: " + form.getFormName() + configBean.getClassNameSuffix() + " " + form.getComment() + "\n" +
                " *\n" +
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
        // serialVersionUID
        classContentBuilder.append(buildserialVersionUID()).append("\n\n");
        // Attribute
        classContentBuilder.append(buildClassAttribute(form.getFormField(), form.isContainCommentField())).append("\n\n\n");
        // NoArgsConstructor
        classContentBuilder.append(buildNoArgsConstructor(form, configBean)).append("\n\n");
        // AllArgsConstructor
        classContentBuilder.append(buildAllArgsConstructor(form, configBean)).append("\n\n");
        // AllSetterGetter
        classContentBuilder.append(
                buildAllSetterGetter(form.getFormName() + configBean.getClassNameSuffix(), form.getFormField(), configBean.isSetterReturnThis(), form.isContainCommentField())
        ).append("\n\n");
        // ToString
        classContentBuilder.append(buildToString(form, configBean.getClassNameSuffix())).append("\n");
        // }
        classContentBuilder.append("}");

        return classContentBuilder;
    }


    private static StringBuilder buildClassHeader(Form form, AutoBuildCodeConfigBean configBean) {

        StringBuilder builder = new StringBuilder("public class " + form.getFormName() + configBean.getClassNameSuffix());

        if (form.isContainCommentField()) {
            builder.append(" extends BaseEntity");
        } else {
            builder.append(" implements Serializable");
        }

        return builder;
    }


    private static StringBuilder buildserialVersionUID() {

        return new StringBuilder("    private static final long serialVersionUID = 1L;");
    }


    private static StringBuilder buildClassAttribute(List<FormField> formFieldList, boolean containCommentField) {

        StringBuilder attributeBuilder = new StringBuilder();

        if (formFieldList != null && formFieldList.size() > 0) {

            List<String> baseEntityFields = new ArrayList<>();
            if (containCommentField) {
                baseEntityFields = BuildHelper.getBaseEntityAttributes();
            }

            for (FormField formField : formFieldList) {

                // 如果表单配置中明确或默认 containCommentField = true
                // 即表单配置继承 BaseEntity,拥有表单基础字段 id,uuid,deleted ...
                // 则无需再配置表单基础字段，集成的 BaseEntity 中已经包含
                if (containCommentField && baseEntityFields.contains(formField.getField())) {
                    continue;
                }

                if (attributeBuilder.length() > 0) {
                    attributeBuilder.append("\n").append("\n");
                }

                String comment = formField.getComment();
                if (notNull_Empty(comment)) {
                    attributeBuilder.append("    /*** " + formField.getComment() + " */\n");
                } else {
                    attributeBuilder.append("    /*** " + formField.getField() + " */\n");
                }
                attributeBuilder.append("    private " + FieldType.getOnlyClassName(formField.getFieldType()) + " " + formField.getField()).append(";");
            }
        }

        return attributeBuilder;
    }


    private static StringBuilder buildNoArgsConstructor(Form form, AutoBuildCodeConfigBean configBean) {

        return new StringBuilder(
                "    public " + form.getFormName() + configBean.getClassNameSuffix() + "() {\n" +
                        "    }"
        );
    }


    private static StringBuilder buildAllArgsConstructor(Form form, AutoBuildCodeConfigBean configBean) {

        // 参数
        String formFieldParam = "";
        // 属性赋值
        StringBuilder assignmentBuilder = new StringBuilder();

        List<FormField> formFieldList = form.getFormField();
        List<String> baseEntityFields = BuildHelper.getBaseEntityAttributes();

        if (form.isContainCommentField()) {
            StringBuilder tempBuild = new StringBuilder();
            for (String name : baseEntityFields) {
                if (tempBuild.length() > 0) {
                    tempBuild.append(", ");
                }
                tempBuild.append(name);
            }
            assignmentBuilder.append("        super(").append(tempBuild).append(");");
        }

        if (formFieldList != null && formFieldList.size() > 0) {

            for (FormField itemField : formFieldList) {

                if (formFieldParam != null && formFieldParam.length() > 0) {
                    formFieldParam += ", ";
                }
                formFieldParam = formFieldParam + FieldType.getOnlyClassName(itemField.getFieldType()) + " " + itemField.getField();

                if (form.isContainCommentField() && baseEntityFields.contains(itemField.getField())) {
                    continue;
                }

                if (assignmentBuilder.length() > 0) {
                    assignmentBuilder.append("\n");
                }
                assignmentBuilder.append(buildFormFieldAssignment(itemField));
            }
        }

        return new StringBuilder(
                "    public " + form.getFormName() + configBean.getClassNameSuffix() + "(" + formFieldParam + ") {\n" +
                        assignmentBuilder + "\n" +
                        "    }"
        );
    }


    private static StringBuilder buildAllSetterGetter(String ClassName, List<FormField> formFieldList, boolean returnThis, boolean containCommentField) {

        StringBuilder builder = new StringBuilder();
        if (formFieldList != null && formFieldList.size() > 0) {

            List<String> baseEntityFields = new ArrayList<>();
            if (containCommentField) {
                baseEntityFields = BuildHelper.getBaseEntityAttributes();
            }

            for (FormField formField : formFieldList) {
                // 如果表单配置中明确或默认 containCommentField = true
                // 即表单配置继承 BaseEntity,拥有表单基础字段 id,uuid,deleted ...
                // 则无需再配置表单基础字段的 Setter Getter，集成的 BaseEntity 中已经包含
                if (containCommentField && baseEntityFields.contains(formField.getField())) {
                    continue;
                }

                if (builder.length() > 0) {
                    builder.append("\n").append("\n");
                }
                builder.append(buildSingleSetterGetter(ClassName, formField, returnThis));
            }
        }
        return builder;
    }


    private static StringBuilder buildSingleSetterGetter(String clazzName, FormField formField, boolean returnThis) {

        StringBuilder setterGetterBuilder = new StringBuilder();

        setterGetterBuilder.append(buildSetter(clazzName, formField, returnThis));
        setterGetterBuilder.append("\n\n");
        setterGetterBuilder.append(buildGetter(formField, returnThis));

        return setterGetterBuilder;
    }


    private static StringBuilder buildSetter(String ClassName, FormField formField, boolean returnThis) {

        StringBuilder builder = new StringBuilder("    public ");
        if (returnThis) {
            builder.append(ClassName);
        } else {
            builder.append("void");
        }
        builder
                .append(" set")
                .append(formField.getField().substring(0, 1).toUpperCase() + formField.getField().substring(1))
                .append("(")
                .append(FieldType.getOnlyClassName(formField.getFieldType()) + " " + formField.getField())
                .append(")");

        builder
                .append(" {").append("\n")
                .append(buildFormFieldAssignment(formField)).append("\n")
                .append(returnThis(returnThis)).append("\n")
                .append("    }");

        return builder;
    }


    private static StringBuilder buildGetter(FormField formField, boolean returnThis) {

        StringBuilder builder = new StringBuilder("    public ");
        if (returnThis) {
            builder.append(FieldType.getOnlyClassName(formField.getFieldType()));
        } else {
            builder.append("void ");
        }

        builder
                .append(" get")
                .append(formField.getField().substring(0, 1).toUpperCase() + formField.getField().substring(1))
                .append("(")
                .append(")");

        builder
                .append(" {").append("\n")
                .append("        return this." + formField.getField()).append(";\n")
                .append("    }");

        return builder;
    }


    private static StringBuilder buildToString(Form form, String classNameSuffix) {

        StringBuilder tempBuilder = new StringBuilder();

        List<String> baseAttriList = new ArrayList<>();
        if (form.isContainCommentField()) {
            baseAttriList = BuildHelper.getBaseEntityAttributes();
        }

        List<FormField> formFieldlist = form.getFormField();
        if (formFieldlist != null && formFieldlist.size() > 0) {

            for (FormField item : formFieldlist) {
                if (baseAttriList.contains(item.getField())) {
                    if (tempBuilder.length() == 0) {
                        tempBuilder.append("                \"" + item.getField() + "='\" + get" + item.getField().substring(0, 1).toUpperCase() + item.getField().substring(1) + "() + '\\'' +\n");
                    } else {
                        tempBuilder.append("                \", " + item.getField() + "='\" + get" + item.getField().substring(0, 1).toUpperCase() + item.getField().substring(1) + "() + '\\'' +\n");
                    }
                } else {
                    if (tempBuilder.length() == 0) {
                        tempBuilder.append("                \"" + item.getField() + "='\" + " + item.getField() + " + '\\'' +\n");
                    } else {
                        tempBuilder.append("                \", " + item.getField() + "='\" + " + item.getField() + " + '\\'' +\n");
                    }
                }
            }
        }

        return new StringBuilder("" +
                "    @Override\n" +
                "    public String toString() {\n" +
                "        return \"" + form.getFormName() + classNameSuffix + "{\" +\n" +
                tempBuilder +
                "                '}';\n" +
                "    }\n");
    }


    private static StringBuilder buildFormFieldAssignment(FormField formField) {

        StringBuilder builder = new StringBuilder();
        builder.append("        this." + formField.getField() + " = " + formField.getField() + ";");
        return builder;
    }

    private static StringBuilder returnThis(boolean returnThis) {
        StringBuilder builder = new StringBuilder();
        if (returnThis) {
            builder.append("        return this;");
        }
        return builder;
    }
}
