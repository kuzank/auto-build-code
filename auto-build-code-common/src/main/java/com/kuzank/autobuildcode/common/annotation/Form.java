package com.kuzank.autobuildcode.common.annotation;

import java.lang.annotation.*;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/9/15
 */

/*** javadoc */
@Documented
/*** 注可以被继承 */
@Inherited
/*** 标明该注解可以用于类、接口（包括注解类型）或enum声明 */
@Target({ElementType.TYPE})
/*** 注解的生命周期 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Form {

    /*** 数据库表名,默认为 JavaBean 名称 (驼峰转化)*/
    String collection() default "";


    /*** JavaBean 类名 (驼峰) */
    String formName() default "";


    /*** 表单的显示名称 */
    String showName() default "";


    /*** 数据库表单描述 */
    String comment() default "";


    /*** 表单是否包含默认字段,id,uuid,deleted,createdBy,createDate,updatedBy,updatedDate,description,默认为 ture */
    boolean containCommentField() default true;
}
