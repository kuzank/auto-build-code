package com.kuzank.autobuildcode.common;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/9/18
 */
public class CommonTest {

    @Test
    public void JavaBeanTemplateTest() {

    }

    @Test
    public void BaseEntityTest() {
        System.out.println(BaseEntity.class.getName());
    }

    @Test
    public void classPath() throws IOException {

        // 第一种：获取类加载的根路径   D:\git\daotie\daotie\target\classes
        File f = new File(this.getClass().getResource("/").getPath());
        System.out.println(f);

        // 获取当前类的所在工程路径; 如果不加“/”  获取当前类的加载目录  D:\git\daotie\daotie\target\classes\my
        File f2 = new File(this.getClass().getResource("").getPath());
        System.out.println(f2);

        // 第二种：获取项目路径    D:\git\daotie\daotie
        File directory = new File("");// 参数为空
        String courseFile = directory.getCanonicalPath();
        System.out.println(courseFile);


        // 第三种：  file:/D:/git/daotie/daotie/target/classes/
        URL xmlpath = this.getClass().getClassLoader().getResource("");
        System.out.println(xmlpath);
    }

    @Test
    public void fileSeparator() {
        System.out.println(File.separator);
    }

}
