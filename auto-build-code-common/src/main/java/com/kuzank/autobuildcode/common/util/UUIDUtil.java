package com.kuzank.autobuildcode.common.util;

import java.util.UUID;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/9/22
 */
public class UUIDUtil {


    public static String get32() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
