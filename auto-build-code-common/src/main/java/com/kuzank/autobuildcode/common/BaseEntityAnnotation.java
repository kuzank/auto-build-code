package com.kuzank.autobuildcode.common;

import com.kuzank.autobuildcode.common.annotation.Form;
import com.kuzank.autobuildcode.common.annotation.FormField;
import com.kuzank.autobuildcode.common.enumeration.FieldType;

import java.util.Date;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/9/19
 */
@Form
public class BaseEntityAnnotation {

    @FormField(name = "ID", fieldType = FieldType.INTEGER, unique = true, primary = true, comment = "主键 Integer 自增")
    private Integer id;

    @FormField(name = "UUID", fieldType = FieldType.VARCHAR, length = 32, unique = true, comment = "唯一主键, 32 位 UUID")
    private String uuid;

    @FormField(name = "是否删除", fieldType = FieldType.BOOLEAN, defaultValue = "0", comment = "是否删除, 1 表示删除 , 0 不删除")
    private Boolean deleted;

    @FormField(name = "创建人", fieldType = FieldType.INTEGER, comment = "当前记录的创建人ID")
    private Integer createdBy;

    @FormField(name = "更新人", fieldType = FieldType.INTEGER, comment = "当前记录的更新人ID")
    private Integer updatedBy;

    @FormField(name = "创建时间", fieldType = FieldType.DATETIME, comment = "当前记录的创建时间")
    private Date createdAt;

    @FormField(name = "更新时间", fieldType = FieldType.DATETIME, comment = "当前记录的更新时间")
    private Date updatedAt;

    @FormField(name = "描述", fieldType = FieldType.VARCHAR, length = 255, index = true, comment = "备注信息")
    private String description;

}
