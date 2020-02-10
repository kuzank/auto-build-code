package com.kuzank.autobuildcode.common.util;

import org.junit.Test;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/9/22
 */
public class UUIDUtilTest {

    @Test
    public void get32Test() {

        String uuid = UUIDUtil.get32();

        System.out.println("  uuid : " + uuid);
        System.out.println("length : " + uuid.length());
    }

}
