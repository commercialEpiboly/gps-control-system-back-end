package org.yzh.web.context;



import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.Map;

/**
 * 操作Holder
 * @Author: lb
 * @Date: 2021/8/24 15:43
 */
public class OperatorContextHolder {

    private static final ThreadLocal<Map<String, String>> CONTEXT = new TransmittableThreadLocal<>();

    public static void setOperator(Map<String, String> map) {
        CONTEXT.set(map);
    }

    public static Map<String, String> getOperator() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
