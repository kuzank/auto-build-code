package com.kuzank.autobuildcode.commander.helper;

/**
 * <p>Description: Constants 是本 WEB 应用的常用性变量,取消对常用性变量进行硬编码，提高通用性与可读性。</p>
 *
 * @author kuzank
 */
public class Constants {
    public static final String USER_SESSION_KEY = "user";

    /**
     * VIEW MAPPING
     */
    public static final String LOGIN_VIEW = "/login";
    public static final String INDEX_VIEW = "/index";

    public static final String HTTP_400_VIEW = "/error/400";
    public static final String HTTP_401_VIEW = "/error/401";
    public static final String HTTP_404_VIEW = "/error/404";
    public static final String HTTP_500_VIEW = "/error/500";


}
