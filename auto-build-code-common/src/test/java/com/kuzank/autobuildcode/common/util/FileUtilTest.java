package com.kuzank.autobuildcode.common.util;

import org.junit.Test;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/9/25
 */
public class FileUtilTest {

    @Test
    public void writeToDisk() throws Exception {

        String str = "test write string to disk";
        String path = "e://write.txt";

        boolean result = FileUtil.writeToDisk(str, path);
        System.out.println(result);
    }

}
