package com.kuzank.autobuildcode.common.util;

import com.kuzank.autobuildcode.common.compiler.JavaStringCompiler;

import java.io.IOException;
import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/9/28
 */
public class ClassUtil {

    /**
     * 将 String 转化为 java.lang.Class
     *
     * @param packageName  com.kuzank.bean.Student
     * @param className    Student.java
     * @param classContent public class Student { ...}
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static final Class compiler(String packageName, String className, String classContent) throws IOException, ClassNotFoundException {

        JavaStringCompiler javaStringCompiler = new JavaStringCompiler();

        Map<String, byte[]> compileMap = javaStringCompiler.compile(className, classContent);

        return javaStringCompiler.loadClass(packageName, compileMap);
    }

}
