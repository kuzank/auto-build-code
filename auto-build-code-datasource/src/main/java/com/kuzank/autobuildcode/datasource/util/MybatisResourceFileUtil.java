package com.kuzank.autobuildcode.datasource.util;

import com.sun.tools.javac.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/9/13
 */
public class MybatisResourceFileUtil {

    private static final Logger logger = LoggerFactory.getLogger(MybatisResourceFileUtil.class);

    public static final String FILE_SUFFIX = "xml";

    /**
     * 迭代获取路径 path 下的所有文件
     *
     * @param filePathList 存储资源路径下的所有文件
     * @param absolutePath 绝对资源路径
     * @return
     */
    public static List<String> readFile(List<String> filePathList, String absolutePath) {

        Assert.checkNonNull(absolutePath, "Param path require not null");

        if (filePathList == null) {
            filePathList = new ArrayList<>();
        }

        File[] tempList = new File(absolutePath).listFiles();

        if (tempList != null && tempList.length > 0) {

            for (File f : tempList) {

                if (f.isFile()) {
                    if (f.getPath().contains(FILE_SUFFIX)) {
                        filePathList.add(f.getPath());
                    } else {
                        logger.warn("mybatis configuration path contain files that was no expect : " + f.getPath());
                    }
                } else {
                    readFile(filePathList, f.getPath());
                }
            }
        }
        return filePathList;
    }


}
