package com.kuzank.autobuildcode.common.util;

import com.kuzank.autobuildcode.common.exception.AutoBuildCodeException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/9/17
 */
public class FileUtil {

    /**
     * 将 fileContent 写入 filePath
     *
     * @param fileContent
     * @param filePath
     * @return
     */
    public static boolean writeToDisk(String fileContent, String filePath) throws AutoBuildCodeException, IOException {

        if (filePath == null || filePath.length() == 0) {
            throw new AutoBuildCodeException("FileUtil.writeToDisk : param filePath required not null and not empty");
        }

        if (fileContent == null) {
            throw new AutoBuildCodeException("FileUtil.writeToDisk : param fileContent required not null");
        }

        File file = new File(filePath);

        if (file.exists()) {
            throw new AutoBuildCodeException("FileUtil.writeToDisk : file already exist --> " + filePath);
        } else {

            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                if (!parentFile.mkdir()) {
                    throw new AutoBuildCodeException("FileUtil.writeToDisk : false to mkdir parent path --> " + filePath);
                }
            }

            if (!file.createNewFile()) {
                throw new AutoBuildCodeException("FileUtil.writeToDisk : false to create file --> " + filePath);
            }

            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(fileContent.getBytes());

            outputStream.flush();
            outputStream.close();
        }

        return true;
    }

}
