package com.kuzank.autobuildcode.commander.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * <p>Description: 获取系统中 kaptcha 验证码配置信息 </p>
 *
 * @author kuzank  2018/8/21
 */
@Configuration
@ConfigurationProperties(prefix = "kaptcha")
@PropertySource(value = "classpath:kaptcha.properties")
@Data
public class KaptchaSetting {

    private String border = "yes";
    private String textproducer_font_color = "black";
    private String image_width = "124";
    private String image_height = "45";
    private String textproducer_char_length = "4";
    private String textproducer_char_space = "5";
    private String textproducer_char_string = "1234567890";
    private String textproducer_font_names = "宋体,楷体,微软雅黑";

}
