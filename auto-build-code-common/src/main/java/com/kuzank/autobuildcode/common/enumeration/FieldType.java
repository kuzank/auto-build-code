package com.kuzank.autobuildcode.common.enumeration;

import com.kuzank.autobuildcode.common.exception.AutoBuildCodeException;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>Description: 数据库表单字段类型、</p>
 *
 * @author kuzank  2018/9/14
 */
public enum FieldType {

    /*** 默认类型 */
    DEFAULT("String", "CHAR", 255),

    /*** 字符串类型 */
    // 	0-255字节,定长字符串
    CHAR("String", "CHAR", 255),
    // 0-65 535 字节,变长字符串
    VARCHAR("String", "VARCHAR", 65535),
    // 0-65 535字节,长文本数据
    TEXT("String", "VARCHAR"),
    // 0-16 777 215字节,中等长度文本数据
    MEDIUMTEXT("String", "VARCHAR"),
    // 0-4 294 967 295字节,极大文本数据
    LONGTEXT("String", "VARCHAR"),

    /*** 布尔值 */
    BIT("Boolean", "BIT"),
    BOOLEAN("Boolean", "BOOLEAN"),

    /*** 数值类型 */
    // 2 byte
    SMALLINT("Integer", "SMALLINT"),
    // 4 byte
    INTEGER("Integer", "INTEGER"),
    // 8 byte
    BIGINT("Long", "BIGINT"),
    // 8 tybe
    DOUBLE("Double", "DOUBLE"),
    // 依赖于M和D的值,对DECIMAL(M,D) ，如果M>D，为M+2否则为D+2
    DECIMAL("java.math.BigDecimal", "DECIMAL"),

    // YYYY-MM-DD HH:MM:SS --> 1000-01-01 00:00:00/9999-12-31 23:59:59
    DATETIME("java.util.Date", "DATETIME"),


    /*** 二进制 */
    // 0-65 535字节,二进制形式的长文本数据
    BINARY("byte[]", "BINARY"),
    BLOB("byte[]", "BLOB"),
    TINYBLOB("byte[]", "TINYBLOB"),
    LONGBLOB("byte[]", "LONGBLOB"),
    ;

    private String javaType;
    private String jdbcType;
    private int length;

    FieldType(String javaType, String jdbcType, int length) {
        this.javaType = javaType;
        this.jdbcType = jdbcType;
        this.length = length;
    }

    FieldType(String javaType, String jdbcType) {
        this.javaType = javaType;
        this.jdbcType = jdbcType;
        this.length = 0;
    }

    public String getJavaType() {
        return javaType;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public int getLength() {
        return length;
    }


    public static FieldType getByJdbcType(String typeName) throws AutoBuildCodeException {

        if (typeName == null || typeName.length() == 0) {
            throw new AutoBuildCodeException("param require not null and not empty");
        }

        FieldType fieldType;

        try {
            fieldType = FieldType.valueOf(typeName);

        } catch (Exception e) {
            throw new AutoBuildCodeException("FormField’s FieldType not support JdbcType of \"" + typeName + "\"");
        }

        return fieldType;
    }

    public static FieldType getByJavaType(Class clazz) throws AutoBuildCodeException {

        if (clazz == null) {
            throw new AutoBuildCodeException("param require not null");
        }

        /*** String */
        if (clazz == String.class) {
            return FieldType.VARCHAR;
        }
        /*** Integer && int */
        else if (clazz == Integer.class || clazz == int.class) {
            return FieldType.INTEGER;
        }
        /*** long && Long */
        else if (clazz == long.class || clazz == Long.class) {
            return FieldType.BIGINT;
        }
        /*** BigDecimal */
        else if (clazz == BigDecimal.class) {
            return FieldType.DECIMAL;
        }
        /*** float && Float && double && Double */
        else if (clazz == float.class || clazz == Float.class || clazz == double.class || clazz == Double.class) {
            return FieldType.DOUBLE;
        }
        /*** boolean && Boolean */
        else if (clazz == boolean.class || clazz == Boolean.class) {
            return FieldType.BOOLEAN;
        }
        /*** java.util.Date */
        else if (clazz == java.util.Date.class) {
            return FieldType.DATETIME;
        }
        /*** byte[] */
        else if (clazz == byte[].class) {
            return FieldType.BINARY;
        }

        throw new AutoBuildCodeException("FormField’s FieldType not support JavaType of " + clazz.getName());
    }

    public static String getOnlyClassName(FieldType fieldType) {
        if (fieldType == DECIMAL) {
            return BigDecimal.class.getSimpleName();
        } else if (fieldType == DATETIME) {
            return Date.class.getSimpleName();
        } else {
            return fieldType.getJavaType();
        }
    }

    @Override
    public String toString() {
        return "FieldType{" +
                "javaType='" + javaType + '\'' +
                ", jdbcType='" + jdbcType + '\'' +
                ", length=" + length +
                '}';
    }
}

