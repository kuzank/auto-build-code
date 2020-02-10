package com.kuzank.autobuildcode.datasource.temp;

import com.kuzank.autobuildcode.common.BaseEntity;

import java.util.Date;

/**
 * <p> Description: SchoolStudentPO 表单存储学校学生的基本信息
 *
 * <p> @author auto-build-code
 * <p> @date   2018-09-25T20:04:58.928
 */
public class SchoolStudentPO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /*** name */
    private String name;

    /*** age */
    private Integer age;

    /*** birthday */
    private Date birthday;

    /*** merried */
    private Boolean merried;

    /*** heigth */
    private Double heigth;


    public SchoolStudentPO() {
    }

    public SchoolStudentPO(Integer id, String uuid, Boolean deleted, Integer createdBy, Integer updatedBy, Date createdAt, Date updatedAt, String description, String name, Integer age, Date birthday, Boolean merried, Double heigth) {
        super(id, uuid, deleted, createdBy, updatedBy, createdAt, updatedAt, description);
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.merried = merried;
        this.heigth = heigth;
    }

    public SchoolStudentPO setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public SchoolStudentPO setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Integer getAge() {
        return this.age;
    }

    public SchoolStudentPO setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public SchoolStudentPO setMerried(Boolean merried) {
        this.merried = merried;
        return this;
    }

    public Boolean getMerried() {
        return this.merried;
    }

    public SchoolStudentPO setHeigth(Double heigth) {
        this.heigth = heigth;
        return this;
    }

    public Double getHeigth() {
        return this.heigth;
    }

    @Override
    public String toString() {
        return "SchoolStudentPO{" +
                "id='" + getId() + '\'' +
                ", uuid='" + getUuid() + '\'' +
                ", deleted='" + getDeleted() + '\'' +
                ", createdBy='" + getCreatedBy() + '\'' +
                ", updatedBy='" + getUpdatedBy() + '\'' +
                ", createdAt='" + getCreatedAt() + '\'' +
                ", updatedAt='" + getUpdatedAt() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", birthday='" + birthday + '\'' +
                ", merried='" + merried + '\'' +
                ", heigth='" + heigth + '\'' +
                '}';
    }

}