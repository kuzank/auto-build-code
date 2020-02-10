package com.kuzank.autobuildcode.common;

import com.kuzank.autobuildcode.common.util.UUIDUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Description: 数据库表单基础类</p>
 *
 * @author kuzank
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -8668855393531379578L;

    /**
     * 主键 Integer自增
     **/
    private Integer id = 0;

    /**
     * UUID
     **/
    private String uuid = UUIDUtil.get32();

    /**
     * 是否删除
     **/
    private Boolean deleted = false;

    /**
     * 创建人ID
     **/
    private Integer createdBy;

    /**
     * 更新时间
     **/
    private Integer updatedBy;

    /**
     * 创建时间
     **/
    private Date createdAt;

    /**
     * 更新人ID
     **/
    private Date updatedAt;

    /**
     * 备注
     **/
    private String description;


    public BaseEntity() {
    }

    public BaseEntity(Integer id, String uuid, Boolean deleted, Integer createdBy, Integer updatedBy, Date createdAt, Date updatedAt, String description) {
        this.id = id;
        this.uuid = uuid;
        this.deleted = deleted;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.description = description;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public BaseEntity setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public BaseEntity setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public BaseEntity setDeleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public BaseEntity setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public BaseEntity setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public BaseEntity setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public BaseEntity setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BaseEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", deleted=" + deleted +
                ", createdBy=" + createdBy +
                ", updatedBy=" + updatedBy +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", description='" + description + '\'' +
                '}';
    }
}
