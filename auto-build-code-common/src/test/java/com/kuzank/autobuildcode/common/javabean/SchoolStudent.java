package com.kuzank.autobuildcode.common.javabean;

import com.kuzank.autobuildcode.common.annotation.Form;
import com.kuzank.autobuildcode.common.annotation.FormField;

import java.util.Date;

/**
 * <p>Description: 自动生成代码的 JavaBean 对象</p>
 * <p> 如果不对 JavaBean 对象 SchoolStudent 进行注解,则默认注解信息为
 * <p> @Form(collection = "school_student", collectionName = "school_student", comment = "autoBuildCode_form_comment", containCommentField = true)
 * <p> @Form 注解说明
 * <p>     collection : 数据库表名,默认为 JavaBean 名称 (驼峰转化) --> school_student
 * <p>     collectionName : 数据库中文名,默认为 JavaBean 名称 (驼峰转化) --> school_student
 * <p>     comment : 数据库表单描述,默认为 "autoBuildCode_form_comment"
 * <p>     containCommentField : 表单是否包含默认字段,id,uuid,deleted,createdBy,createDate,updatedBy,updatedDate,description,默认为 ture
 *
 * @author kuzank  2018/9/15
 */
@Form(comment = "表单存储学校学生的基本信息")
public class SchoolStudent {

    @FormField
    private String name;

    @FormField
    private Integer age;

    @FormField
    private Date birthday;

    @FormField
    private Boolean merried;

    @FormField
    private Double heigth;

}
