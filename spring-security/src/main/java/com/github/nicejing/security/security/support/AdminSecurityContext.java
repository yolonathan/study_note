package com.github.nicejing.security.security.support;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nathan
 */
public class AdminSecurityContext {

    private AdminSecurityContext() {
    }

    private static final ThreadLocal<Map<String, String>> THREAD_LOCAL = new ThreadLocal<>();

    private static class ContextHolder {
        private final static AdminSecurityContext ADMIN_SECURITY_CONTEXT = new AdminSecurityContext();
    }

    public static AdminSecurityContext getContext() {
        return ContextHolder.ADMIN_SECURITY_CONTEXT;
    }

    public void set(String key, String value) {
        Map<String, String> map = THREAD_LOCAL.get();
        if (map == null) {
            map = new HashMap<>(16);
            THREAD_LOCAL.set(map);
        }
        map.put(key, value);
    }

    public String get(String key) {
        Map<String, String> map = THREAD_LOCAL.get();
        if (map == null) {
            map = new HashMap<>(16);
            THREAD_LOCAL.set(map);
        }
        return map.get(key);

    }

    public void remove(String key) {
        THREAD_LOCAL.remove();
    }
}
