/*
 * Copyright 1998-2012 360buy.com All right reserved. This software is the confidential and proprietary information of
 * 360buy.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with 360buy.com.
 */
package org.peanut.common.event.util;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;

/**
 * 类ClassUtils.java的实现描述：对class操作的工具类.
 * 
 * @author liulin 2012-2-10 下午05:17:15
 */
public class ClassUtils {

    public ClassUtils(){
    }

    /**
     * @param cls
     * @return
     */
    public static Class<?> getGenericClass(Class<?> cls) {
        return getGenericClass(cls, 0);
    }

    /**
     * 获取对应的class.
     * 
     * @param cls
     * @param i
     * @return
     */
    public static Class<?> getGenericClass(Class<?> cls, int i) {
        Object genericClass;
        try {
            ParameterizedType parameterizedType = (ParameterizedType) cls.getGenericInterfaces()[0];
            genericClass = parameterizedType.getActualTypeArguments()[i];
            if (genericClass instanceof ParameterizedType) {
                return (Class<?>) ((ParameterizedType) genericClass).getRawType();
            }
        } catch (Throwable e) {
            throw new IllegalArgumentException(
                                               (new StringBuilder()).append(cls.getName()).append(" generic type undefined!").toString(),
                                               e);
        }
        if (genericClass instanceof GenericArrayType) {
            return (Class<?>) ((GenericArrayType) genericClass).getGenericComponentType();
        }

        return (Class<?>) genericClass;
    }

    /**
     * 原始类型转换成包装类型.
     * 
     * @param cls 目标class object.
     * @return
     */
    public static Class<?> convertPrimitiveClass(Class<?> cls) {
        if (cls.isPrimitive()) {
            if (cls == Boolean.TYPE) return Boolean.class;
            if (cls == Character.TYPE) return Character.class;
            if (cls == Byte.TYPE) return Byte.class;
            if (cls == Short.TYPE) return Short.class;
            if (cls == Integer.TYPE) return Integer.class;
            if (cls == Long.TYPE) return Long.class;
            if (cls == Float.TYPE) return Float.class;
            if (cls == Double.TYPE) return Double.class;
        }
        return cls;
    }

}
