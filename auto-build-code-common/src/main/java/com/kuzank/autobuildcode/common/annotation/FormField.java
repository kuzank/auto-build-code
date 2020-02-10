package com.kuzank.autobuildcode.common.annotation;

import com.kuzank.autobuildcode.common.enumeration.FieldType;

import java.lang.annotation.*;


/**
 * <p>Description: 数据库表单字段注解</p>
 *
 * @author kuzank  2018/9/15
 */

/*** javadoc */
@Documented
/*** 注可以被继承 */
@Inherited
/*** 标明该注解可以用于字段(域)声明 */
@Target({ElementType.FIELD})
/*** 注解的生命周期 */
@Retention(RetentionPolicy.RUNTIME)
public @interface FormField {

    /*** JavaBean 中的属性驼峰名称 (英文)  */
    String field() default "";

    /*** 数据库表单字段名称 (英文),默认为 JavaBean 的属性名称 (驼峰转化) */
    String column() default "";

    /*** 字段的中文名称 */
    String name() default "";

    /*** 数据库表单字段类型 */
    FieldType fieldType() default FieldType.DEFAULT;

    /*** 长度 */
    int length() default 0;

    /*** 是否唯一 */
    boolean unique() default false;

    /*** 是否主键 */
    boolean primary() default false;

    /*** 是否为空 */
    boolean canBeNull() default false;

    /*** 数据库表单字段的默认值 */
    String defaultValue() default "";

    /*** 是否索引 */
    boolean index() default false;

    /*** 数据库字段描述 */
    String comment() default "";

}
