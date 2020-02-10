package com.kuzank.autobuildcode.commander.autobuildcode;

import com.kuzank.autobuildcode.common.annotation.Form;

import java.util.Date;

/**
 * <p>Description: 自动生成代码的 JavaBean 对象</p>
 * <p> 如果不对 JavaBean 对象 SchoolStudent 进行注解,则默认注解信息为
 * <p> @Form(collection = "school_student", collectionName = "school_student", comment = "autoBuildCode_form_comment", containCommentField = true)
 * <p> @Form 注解说明
 * <p>     collection : 数据库表名,默认为 JavaBean 名称 (驼峰转化) --> school_student
 * <p>       formName : JavaBean 类名 (驼峰)  --> SchoolStudent + ( ClassSuffix )
 * <p>       showName : 表单的显示名称
 * <p>        comment : 数据库表单描述,默认为 "autoBuildCode_form_comment"
 * <p>     containCommentField : 表单是否包含默认字段,id,uuid,deleted,createdBy,createDate,updatedBy,updatedDate,description,默认为 ture
 *
 * @author kuzank  2018/9/15
 */
@Form(comment = "表单存储学校学生的基本信息")
public class Student {

    private String name;

    private Integer age;

    private Date birthday;

    private Boolean merried;

    private Double heigth;
}
