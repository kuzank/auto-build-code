package com.kuzank.autobuildcode.datasource.entity.po;


import com.kuzank.autobuildcode.common.BaseEntity;

import java.util.Date;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/7/26
 */
public class BookPO extends BaseEntity {

    private static final long serialVersionUID = 7758096661345566661L;

    private String noteId;
    private String name;
    private String color;
    private Integer sortname;

    public BookPO() {
    }

    public BookPO(Integer id, String uuid, Boolean deleted, Integer createdBy, Integer updatedBy, Date createdAt, Date updatedAt, String description, String name, Integer age, Date birthday, Boolean merried, Double heigth) {
        super(id, uuid, deleted, createdBy, updatedBy, createdAt, updatedAt, description);
        this.noteId = noteId;
        this.name = name;
        this.color = color;
        this.sortname = sortname;
    }

    public String getNoteId() {
        return noteId;
    }

    public BookPO setNoteId(String noteId) {
        this.noteId = noteId;
        return this;
    }

    public String getName() {
        return name;
    }

    public BookPO setName(String name) {
        this.name = name;
        return this;
    }

    public String getColor() {
        return color;
    }

    public BookPO setColor(String color) {
        this.color = color;
        return this;
    }

    public Integer getSortname() {
        return sortname;
    }

    public BookPO setSortname(Integer sortname) {
        this.sortname = sortname;
        return this;
    }

    @Override
    public String toString() {
        return "BookPO{" +
                "id='" + getId() + '\'' +
                ", deleted='" + getDeleted() + '\'' +
                ", createdBy='" + getCreatedBy() + '\'' +
                ", updatedBy='" + getUpdatedBy() + '\'' +
                ", createdAt='" + getCreatedAt() + '\'' +
                ", updatedAt='" + getUpdatedAt() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", noteId='" + noteId + '\'' +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", sortname='" + sortname + '\'' +
                '}';
    }
}
