package com.github.nicejing.security.utils;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author Nathan
 */
@Component
public class SpringContextUtil implements ApplicationContextAware, EnvironmentAware {

    private static ApplicationContext context = null;

    private static Environment        env     = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        env = environment;
    }

    /**
     * 获取国际化消息
     * @param key
     * @return
     */
    public static String getMessage(String key) {
        return context.getMessage(key, null, Locale.getDefault());
    }

    /**
     * 获取当前环境
     * @return
     */
    public static Environment getEnvironment() {
        return env;
    }

    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class<T> requiredType) {
        return context.getBean(beanName, requiredType);
    }

    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

}
