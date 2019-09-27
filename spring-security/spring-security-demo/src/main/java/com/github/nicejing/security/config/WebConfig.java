package com.github.nicejing.security.config;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.parameters.P;

import java.util.Properties;

/**
 * @author Jing Zhi Bao
 */
@Configuration
public class WebConfig {

    @Bean
    public Producer captcha(){

        Properties properties = new Properties();
        // 宽度
        properties.setProperty(Constants.KAPTCHA_IMAGE_WIDTH, "150");
        // 长度
        properties.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT, "150");
        // 字符集
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING, "0123456789");
        // 字符宽度
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");

        Config config = new Config(properties);


        DefaultKaptcha kaptcha = new DefaultKaptcha();
        kaptcha.setConfig(config);

        return kaptcha;




    }
}
