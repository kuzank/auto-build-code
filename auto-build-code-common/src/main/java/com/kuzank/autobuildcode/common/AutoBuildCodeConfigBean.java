package com.kuzank.autobuildcode.common;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/9/17
 */
public class AutoBuildCodeConfigBean {

    private String buildPackage;
    private String classNameSuffix;
    private String authorName;
    private boolean setterReturnThis;

    public AutoBuildCodeConfigBean() {
        /*** 默认代码生成后，保存在本工程 com.kuzank.autobuildcode.datasource.temp 包下  */
        this.buildPackage = "com.kuzank.autobuildcode.datasource.temp";
        /*** 生成 JavaBean 默认后缀是 PO */
        this.classNameSuffix = "PO";
        /*** 作者名称 */
        this.authorName = "auto-build-code";
        /*** Setter 返回对象 */
        this.setterReturnThis = true;
    }

    public AutoBuildCodeConfigBean(String buildPackage, String classNameSuffix, String authorName, boolean setterReturnThis) {
        this.buildPackage = buildPackage;
        this.classNameSuffix = classNameSuffix;
        this.authorName = authorName;
        this.setterReturnThis = setterReturnThis;
    }

    public String getBuildPackage() {
        return buildPackage;
    }

    public AutoBuildCodeConfigBean setBuildPackage(String buildPackage) {
        this.buildPackage = buildPackage;
        return this;
    }

    public String getClassNameSuffix() {
        return classNameSuffix;
    }

    public AutoBuildCodeConfigBean setClassNameSuffix(String classNameSuffix) {
        this.classNameSuffix = classNameSuffix;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public AutoBuildCodeConfigBean setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public boolean isSetterReturnThis() {
        return setterReturnThis;
    }

    public AutoBuildCodeConfigBean setSetterReturnThis(boolean setterReturnThis) {
        this.setterReturnThis = setterReturnThis;
        return this;
    }

    @Override
    public String toString() {
        return "AutoBuildCodeConfigBean{" +
                "buildPackage='" + buildPackage + '\'' +
                ", classNameSuffix='" + classNameSuffix + '\'' +
                ", authorName='" + authorName + '\'' +
                ", setterReturnThis=" + setterReturnThis +
                '}';
    }
}
