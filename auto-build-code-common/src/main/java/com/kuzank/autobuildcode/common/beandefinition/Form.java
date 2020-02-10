package com.kuzank.autobuildcode.common.beandefinition;

import com.kuzank.autobuildcode.common.BaseEntityAnnotation;
import com.kuzank.autobuildcode.common.exception.AutoBuildCodeException;
import com.kuzank.autobuildcode.common.helper.BuildHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * <p>Description: 数据库表单注解</p>
 *
 * @author kuzank  2018/9/14
 */
public class Form {

    /*** 数据库表名,默认为 JavaBean 名称 (驼峰转化)*/
    private String collection;

    /*** JavaBean 类名 (驼峰) */
    private String formName;

    /*** 表单的显示名称 */
    private String showName;

    /*** 数据库表单描述 */
    private String comment;

    /*** 表单是否包含默认字段,id,uuid,deleted,createdBy,createDate,updatedBy,updatedDate,description,默认为 ture */
    private boolean containCommentField;

    /*** 数据库表单字段定义 */
    private LinkedHashMap<String, FormField> formFieldMap;

    public Form() {
    }

    public Form(String collection, String formName, String showName, String comment, boolean containCommentField) throws AutoBuildCodeException {
        this.collection = collection;
        this.formName = formName;
        this.showName = showName;
        this.comment = comment;
        this.containCommentField = containCommentField;

        if (containCommentField == true) {
//            ArrayList<FormField> formFields = new ArrayList<FormField>() {{
//                add(new FormField("id", "id", "ID", FieldType.INTEGER, null, true, true, false, "-1", false, "主键 Integer 自增"));
//                add(new FormField("uuid", "uuid", "UUID", FieldType.VARCHAR, 32, true, false, false, null, false, "唯一主键, 32 位 UUID"));
//                add(new FormField("deleted", "deleted", "是否删除", FieldType.BOOLEAN, null, false, false, false, "0", false, "是否删除, 1 表示删除 , 0 不删除"));
//                add(new FormField("createdBy", "created_by", "创建人", FieldType.INTEGER, null, false, false, true, "-1", false, "当前记录的创建人ID"));
//                add(new FormField("updatedBy", "updated_by", "更新人", FieldType.INTEGER, null, false, false, true, "-1", false, "当前记录的更新人ID"));
//                add(new FormField("createdAt", "created_at", "创建时间", FieldType.DATETIME, null, false, false, true, "-1", false, "当前记录的创建时间"));
//                add(new FormField("updatedAt", "updated_at", "更新时间", FieldType.DATETIME, null, false, false, true, "-1", false, "当前记录的更新时间"));
//                add(new FormField("description", "description", "描述", FieldType.VARCHAR, 255, false, false, true, "", false, "备注信息"));
//            }};
//            addFormField(formFields);
            Field[] fieldList = BaseEntityAnnotation.class.getDeclaredFields();

            if (fieldList != null && fieldList.length > 0) {
                for (Field field : fieldList) {
                    addFormField(BuildHelper.getFormField(field));
                }
            }
        }
    }

    public String getCollection() {
        return collection;
    }

    public Form setCollection(String collection) {
        this.collection = collection;
        return this;
    }

    public String getFormName() {
        return formName;
    }

    public Form setFormName(String formName) {
        this.formName = formName;
        return this;
    }

    public String getShowName() {
        return showName;
    }

    public Form setShowName(String showName) {
        this.showName = showName;
        return this;
    }

    public String getComkkment() {
        return comment;
    }

    public Form setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public boolean isContainCommentField() {
        return containCommentField;
    }

    public Form setContainCommentField(boolean containCommentField) {
        this.containCommentField = containCommentField;
        return this;
    }

    public LinkedHashMap<String, FormField> getFormFieldMap() {

        if (this.formFieldMap == null) {
            this.formFieldMap = new LinkedHashMap<>();
        }
        return formFieldMap;
    }

    public List<FormField> getFormField() {
        if (this.formFieldMap == null) {
            this.formFieldMap = new LinkedHashMap<>();
            return getFormField();
        }
        List<FormField> formFieldList = new ArrayList<>();
        for (FormField formField : formFieldMap.values()) {
            formFieldList.add(formField);
        }
        return formFieldList;
    }

    public Form setFormFieldMap(ArrayList<FormField> formFieldList) throws AutoBuildCodeException {

        if (formFieldList != null && formFieldList.size() > 0) {
            addFormField(formFieldList);
        }
        return this;
    }

    /**
     * 在 Form 表单中添加 FormField 字段属性
     *
     * @param formFields
     * @return
     */
    public Form addFormField(FormField... formFields) throws AutoBuildCodeException {

        if (formFields != null && formFields.length > 0) {

            LinkedHashMap<String, FormField> formFieldMap = getFormFieldMap();

            for (FormField formField : formFields) {

                String field = formField.getField();

                if (field == null || field.length() == 0) {
                    throw new AutoBuildCodeException("FormField’s attributes field require not null and not empty , " + formField);
                } else {

                    if (formFieldMap.containsKey(field) == false) {
                        formFieldMap.put(formField.getField(), formField);

                    } else {
                        throw new AutoBuildCodeException("Current Form contain same FormField definition name : " + formField.getField() + " which was not allowed");
                    }
                }
            }
        }
        return this;
    }

    /**
     * 在 Form 表单中添加 FormField 字段属性
     *
     * @param formFields
     * @return
     */
    public Form addFormField(List<FormField> formFields) throws AutoBuildCodeException {

        if (formFields != null && formFields.size() > 0) {

            LinkedHashMap<String, FormField> formFieldMap = getFormFieldMap();

            for (FormField formField : formFields) {

                String field = formField.getField();

                if (field == null || field.length() == 0) {
                    throw new AutoBuildCodeException("FormField’s attributes field require not null and not empty , " + formField);
                } else {

                    if (formFieldMap.containsKey(field) == false) {
                        formFieldMap.put(formField.getField(), formField);

                    } else {
                        throw new AutoBuildCodeException("Current Form contain same FormField definition name : " + formField.getField() + " which was not allowed");
                    }
                }
            }
        }

        return this;
    }

    @Override
    public String toString() {
        return "Form{" +
                "collection='" + collection + '\'' +
                ", formName='" + formName + '\'' +
                ", showName='" + showName + '\'' +
                ", comment='" + comment + '\'' +
                ", containCommentField=" + containCommentField +
                ", formFieldMap=" + formFieldMap +
                '}';
    }
}
