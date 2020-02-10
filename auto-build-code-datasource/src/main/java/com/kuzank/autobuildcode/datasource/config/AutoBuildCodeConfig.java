package com.kuzank.autobuildcode.datasource.config;

import com.kuzank.autobuildcode.common.AutoBuildCodeConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/9/17
 */
@Configuration
public class AutoBuildCodeConfig {

    public static final String propertyFileName = "/AutoBuildCode.properties";

    private Properties properties = null;

    @Bean
    public AutoBuildCodeConfigBean getAutoBuildCodeConfigBean() {

        loadProperties(propertyFileName);

        AutoBuildCodeConfigBean autoBuildCodeConfigBean =
                new AutoBuildCodeConfigBean(
                        this.properties.getProperty("buildPackage").trim(),
                        this.properties.getProperty("classNameSuffix").trim(),
                        this.properties.getProperty("authorName").trim(),
                        new Boolean(this.properties.getProperty("setterReturnThis"))
                );

        System.out.println(autoBuildCodeConfigBean);

        return autoBuildCodeConfigBean;
    }

    private void loadProperties(String propertyFileName) {
        File propFile = new File(propertyFileName);

        try {
            InputStream is = propFile.isFile() ? new FileInputStream(propFile) : this.getClass().getResourceAsStream(propertyFileName);
            Throwable var4 = null;

            try {
                if (is == null) {
                    throw new IllegalArgumentException("Cannot find property file: " + propertyFileName);
                }

                Properties props = new Properties();
                props.load((InputStream) is);
//                PropertyElf.setTargetFromProperties(this, props);
                this.properties = props;

            } catch (Throwable var14) {
                var4 = var14;
                throw var14;
            } finally {
                if (is != null) {
                    if (var4 != null) {
                        try {
                            ((InputStream) is).close();
                        } catch (Throwable var13) {
                            var4.addSuppressed(var13);
                        }
                    } else {
                        ((InputStream) is).close();
                    }
                }

            }

        } catch (IOException var16) {
            throw new RuntimeException("Failed to read property file", var16);
        }
    }


    public Properties getProperties() {
        return properties;
    }

    public AutoBuildCodeConfig setProperties(Properties properties) {
        this.properties = properties;
        return this;
    }
}
