package com.kuzank.autobuildcode.commander.helper;

/**
 * @author kuzank
 */
public class LoginAuthenticationFailureHandler {

    public static final String CODE_ERROR_URL = "http://localhost:8080/login?code_error";
    public static final String EXPIRED_URL = "http://localhost:8080/login?expired";
    public static final String LOCKED_URL = "http://localhost:8080/login?locked";
    public static final String DISABLED_URL = "http://localhost:8080/login?disabled";

    public static final String PASS_ERROR_URL = "http://localhost:8080/login?pass_error";

    public LoginAuthenticationFailureHandler() {
    }


}
