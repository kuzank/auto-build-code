package com.kuzank.autobuildcode.commander.config;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/8/21
 */
@Configuration
public class KaptchaConfig {

    @Autowired
    private KaptchaSetting kaptchaSetting;

    @Bean(name = "captchaProducer")
    public DefaultKaptcha getKaptchaBean() {

        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();

        Properties properties = new Properties();

        properties.setProperty("kaptcha.border", kaptchaSetting.getBorder());
        properties.setProperty("kaptcha.textproducer.font.color", kaptchaSetting.getTextproducer_font_color());
        properties.setProperty("kaptcha.image.width", kaptchaSetting.getImage_width());
        properties.setProperty("kaptcha.image.height", kaptchaSetting.getImage_height());
        properties.setProperty("kaptcha.session.key", Constants.KAPTCHA_SESSION_KEY);
        properties.setProperty("kaptcha.textproducer.char.length", kaptchaSetting.getTextproducer_char_length());
        properties.setProperty("kaptcha.textproducer.char.space", kaptchaSetting.getTextproducer_char_space());
        properties.setProperty("kaptcha.textproducer.char.string", kaptchaSetting.getTextproducer_char_string());
        properties.setProperty("kaptcha.textproducer.font.names", kaptchaSetting.getTextproducer_font_names());

        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);

        return defaultKaptcha;
    }
}

