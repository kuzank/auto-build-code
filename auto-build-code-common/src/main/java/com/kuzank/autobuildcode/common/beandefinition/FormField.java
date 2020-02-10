package com.kuzank.autobuildcode.common.beandefinition;

import com.kuzank.autobuildcode.common.enumeration.FieldType;

/**
 * <p>Description: 数据库字段定义</p>
 *
 * @author kuzank  2018/9/14
 */
public class FormField {

    /*** JavaBean 中的属性驼峰名称 (英文)  */
    private String field;

    /*** 数据库表单字段名称 (英文),默认为 JavaBean 的属性名称 (驼峰转化) */
    private String column;

    /*** 字段的中文名称 */
    private String name;

    /*** 数据库表单字段类型 */
    private FieldType fieldType;

    /*** 长度 */
    private Integer length;

    /*** 是否唯一 */
    private boolean unique;

    /*** 是否主键 */
    private boolean primary;

    /*** 是否为空 */
    private boolean canBeNull;

    /*** 数据库表单字段的默认值 */
    private String defaultValue;

    /*** 是否索引 */
    private boolean index;

    /*** 数据库字段描述 */
    private String comment;

    public FormField() {
    }

    public FormField(String field, String column, String name, FieldType fieldType, Integer length, boolean unique, boolean primary, boolean canBeNull, String defaultValue, boolean index, String comment) {
        this.field = field;
        this.column = column;
        this.name = name;
        this.fieldType = fieldType;
        this.length = length;
        this.unique = unique;
        this.primary = primary;
        this.canBeNull = canBeNull;
        this.defaultValue = defaultValue;
        this.index = index;
        this.comment = comment;
    }

    public String getField() {
        return field;
    }

    public FormField setField(String field) {
        this.field = field;
        return this;
    }

    public String getColumn() {
        return column;
    }

    public FormField setColumn(String column) {
        this.column = column;
        return this;
    }

    public String getName() {
        return name;
    }

    public FormField setName(String name) {
        this.name = name;
        return this;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public FormField setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
        return this;
    }

    public Integer getLength() {
        return length;
    }

    public FormField setLength(Integer length) {
        this.length = length;
        return this;
    }

    public boolean isUnique() {
        return unique;
    }

    public FormField setUnique(boolean unique) {
        this.unique = unique;
        return this;
    }

    public boolean isPrimary() {
        return primary;
    }

    public FormField setPrimary(boolean primary) {
        this.primary = primary;
        return this;
    }

    public boolean isCanBeNull() {
        return canBeNull;
    }

    public FormField setCanBeNull(boolean canBeNull) {
        this.canBeNull = canBeNull;
        return this;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public FormField setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public boolean isIndex() {
        return index;
    }

    public FormField setIndex(boolean index) {
        this.index = index;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public FormField setComment(String comment) {
        this.comment = comment;
        return this;
    }
}
