package com.kuzank.autobuildcode.common.helper;

import com.kuzank.autobuildcode.common.BaseEntityAnnotation;
import com.kuzank.autobuildcode.common.beandefinition.Form;
import com.kuzank.autobuildcode.common.beandefinition.FormField;
import com.kuzank.autobuildcode.common.enumeration.FieldType;
import com.kuzank.autobuildcode.common.exception.AutoBuildCodeException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.kuzank.autobuildcode.common.util.StringUtil.camelCase2Underscore;
import static com.kuzank.autobuildcode.common.util.StringUtil.notNull_Empty;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/9/16
 */
public class BuildHelper {

    /**
     * 将由 @Form 和 @FormField 修饰的 POJO 的 java.lang.Class 解析为 com.kuzank.autobuildcode.common.beandefinition.Form
     *
     * @param clazz POJO 的 java.lang.Class
     * @return com.kuzank.autobuildcode.common.beandefinition.Form
     */
    public static Form Clazz2WebForm(Class clazz) throws AutoBuildCodeException {

        if (clazz == null) {
            throw new AutoBuildCodeException("param clazz require not null");
        }

        Form form = initForm(clazz);
        return form;
    }

    /**
     * <p> 初始化 Form 表单，包括
     * <p>          collection : 数据库表名,默认为 JavaBean 名称 (驼峰转化)
     * <p>            formName : JavaBean 类名 (驼峰)
     * <p>            showName : 表单的显示名称
     * <p>             comment : 数据库表单描述
     * <p> containCommentField : 表单是否包含默认字段,id,uuid,deleted,createdBy,createDate,updatedBy,updatedDate,description,默认为 ture
     *
     * @param clazz
     */
    private static Form initForm(Class clazz) throws AutoBuildCodeException {

        String collection = camelCase2Underscore(clazz.getSimpleName());
        String formName = clazz.getSimpleName();
        String showName = clazz.getSimpleName();
        String comment = "";
        boolean containCommentField = true;

        Annotation[] FormAnnotations = clazz.getAnnotationsByType(com.kuzank.autobuildcode.common.annotation.Form.class);

        if (FormAnnotations != null && FormAnnotations.length > 0) {
            /*** 一个类中可能包含多个 @Form 注解,只获取第一个 @Form */
            com.kuzank.autobuildcode.common.annotation.Form itemForm = (com.kuzank.autobuildcode.common.annotation.Form) FormAnnotations[0];
            if (notNull_Empty(itemForm.collection())) {
                collection = itemForm.collection();
            }
            if (notNull_Empty(itemForm.formName())) {
                formName = itemForm.formName();
            }
            if (notNull_Empty(itemForm.showName())) {
                showName = itemForm.showName();
            }
            if (notNull_Empty(itemForm.comment())) {
                comment = itemForm.comment();
            }
            containCommentField = itemForm.containCommentField();
        }

        Form form = new Form(collection, formName, showName, comment, containCommentField);

        /*** 初始化 Form 表单中自定义的字段 FormField, 即 JavaBean 的属性 */
        initSpecificFormField(form, clazz);

        return form;
    }

    /**
     * 初始化表单的字段 FormField
     *
     * @param form
     * @param clazz
     */
    private static void initSpecificFormField(Form form, Class clazz) throws AutoBuildCodeException {

        Field[] fields = clazz.getDeclaredFields();

        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                form.addFormField(getFormField(field));
            }
        }
    }

    /**
     * 根据 java.lang.reflect.Field 反射对象生成 com.kuzank.autobuildcode.common.beandefinition.FormField 对象
     *
     * @param field 表单字段的信息 java.lang.reflect.Field
     * @return
     */
    public static FormField getFormField(Field field) throws AutoBuildCodeException {

        field.setAccessible(true);

        String fieldName = field.getName();
        String columnName = camelCase2Underscore(field.getName());
        String name = field.getName();
        FieldType fieldType = FieldType.getByJavaType(field.getType());
        Integer length = fieldType.getLength();
        boolean unique = false;
        boolean primary = false;
        boolean canBeNull = false;
        String defaultValue = "";
        boolean index = false;
        String comment = "";

        Annotation[] FormFieldAnnotations = field.getDeclaredAnnotations();
        if (FormFieldAnnotations != null && FormFieldAnnotations.length > 0) {

            com.kuzank.autobuildcode.common.annotation.FormField formFieldAnno = (com.kuzank.autobuildcode.common.annotation.FormField) FormFieldAnnotations[0];

            if (notNull_Empty(formFieldAnno.field())) {
                fieldName = formFieldAnno.field();
            }
            if (notNull_Empty(formFieldAnno.column())) {
                columnName = formFieldAnno.column();
            }
            if (notNull_Empty(formFieldAnno.name())) {
                name = formFieldAnno.name();
            }
            if (formFieldAnno.fieldType() != null && formFieldAnno.fieldType() != FieldType.DEFAULT) {
                fieldType = formFieldAnno.fieldType();
            }
            if (formFieldAnno.length() > 0) {
                length = formFieldAnno.length();
            }
            if (formFieldAnno.unique() == true) {
                unique = true;
            }
            if (formFieldAnno.primary() == true) {
                primary = true;
            }
            if (formFieldAnno.canBeNull() == true) {
                canBeNull = true;
            }
            if (formFieldAnno.defaultValue() != null) {
                defaultValue = formFieldAnno.defaultValue();
            }
            if (formFieldAnno.index() == true) {
                index = true;
            }
            if (notNull_Empty(formFieldAnno.comment())) {
                comment = formFieldAnno.comment();
            }
        }

        FormField formField = new FormField(fieldName, columnName, name, fieldType, length, unique, primary, canBeNull, defaultValue, index, comment);

        return formField;
    }

    /**
     * 获取基本对象的字段信息
     *
     * @return
     */
    public static List<String> getBaseEntityAttributes() {

        List<String> resultList = new ArrayList<>();

        Field[] fields = BaseEntityAnnotation.class.getDeclaredFields();

        if (fields != null && fields.length > 0) {

            for (Field field : fields) {

                resultList.add(field.getName());
            }
        }

        return resultList;
    }
}
