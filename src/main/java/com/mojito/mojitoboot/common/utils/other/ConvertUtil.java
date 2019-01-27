package com.mojito.mojitoboot.common.utils.other;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ConvertUtil {

    /**
     * 从source拷贝到targetClass实例
     *
     * @param source      {@linkplain Object 转换源实例}
     * @param targetClass {@linkplain Class 转换目标类}
     * @param <T>         泛型
     * @return 转换目标实例
     */
    public static <T> T convert(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        T target = BeanUtils.instantiate(targetClass);
        BeanUtils.copyProperties(source, target);
        //copyProperties(source, target, null);
        return target;
    }

    /**
     * 从source拷贝到targetClass实例
     *
     * @param source           {@linkplain Object 转换源实例}
     * @param targetClass      {@linkplain Class 转换目标类}
     * @param ignoreProperties {@linkplain String 忽略属性}
     * @param <T>              泛型
     * @return 转换目标实例
     */
    public static <T> T convert(Object source, Class<T> targetClass, String[] ignoreProperties) {
        if (source == null) {
            return null;
        }
        T target = BeanUtils.instantiate(targetClass);
        BeanUtils.copyProperties(source, target, ignoreProperties);
        //copyProperties(source, target, ignoreProperties);
        return target;
    }

    /**
     * 拷贝list
     *
     * @param sources     {@linkplain Object 转换源实例列表}
     * @param targetClass {@linkplain Class 转换目标类}
     * @param <T>         泛型
     * @return 转换目标实例列表
     */
    public static <T> List<T> convertList(List<?> sources, Class<T> targetClass) {
        if (sources == null) {
            return null;
        }
        List<T> targets = new ArrayList<>(sources.size());
        for (Object source : sources) {
            targets.add(convert(source, targetClass, null));
        }
        return targets;
    }


    /**
     * 拷贝list
     *
     * @param sources          {@linkplain Object 转换源实例列表}
     * @param targetClass      {@linkplain Class 转换目标类}
     * @param ignoreProperties {@linkplain String 忽略属性}
     * @param <T>              泛型
     * @return 转换目标实例列表
     */
    public static <T> List<T> convertList(List<?> sources, Class<T> targetClass, String[] ignoreProperties) {
        if (sources == null) {
            return null;
        }
        List<T> targets = new ArrayList<>(sources.size());
        for (Object source : sources) {
            targets.add(convert(source, targetClass, ignoreProperties));
        }
        return targets;
    }

    /**
     * 复制属性时，考虑枚举和字符串的转换
     *
     * @param source           {@linkplain Object 拷贝源实例}
     * @param target           {@linkplain Object 拷贝目标实例}
     * @param ignoreProperties {@linkplain String 忽略属性}
     * @throws BeansException 异常
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void copyProperties(Object source, Object target, String[] ignoreProperties) throws BeansException {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");

        Class<?> actualEditable = target.getClass();

        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
        List<String> ignoreList = (ignoreProperties != null) ? Arrays.asList(ignoreProperties) : null;

        for (PropertyDescriptor targetPd : targetPds) {
            if (targetPd.getWriteMethod() != null
                    && (ignoreProperties == null || (!ignoreList.contains(targetPd.getName())))) {
                PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null && sourcePd.getReadMethod() != null) {
                    try {
                        Class sourcePropertyClass = sourcePd.getPropertyType();
                        Class targetPropertyClass = targetPd.getPropertyType();

                        Method readMethod = sourcePd.getReadMethod();
                        if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                            readMethod.setAccessible(true);
                        }
                        Object value = readMethod.invoke(source);
                        if (value != null) {
                            // copy enum
                            if (targetPropertyClass.isEnum() && sourcePropertyClass.isEnum()) {
                                value = Enum.valueOf(targetPropertyClass, value.toString());
                            } else if (targetPropertyClass == String.class && sourcePropertyClass.isEnum()) {
                                value = ((Enum<?>) value).name();
                            } else if (targetPropertyClass.isEnum() && sourcePropertyClass == String.class) {
                                value = Enum.valueOf(targetPropertyClass, (String) value);
                            }
                        }

                        Method writeMethod = targetPd.getWriteMethod();
                        if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                            writeMethod.setAccessible(true);
                        }
                        writeMethod.invoke(target, value);

                    } catch (Throwable ex) {
                        throw new FatalBeanException("Could not copy properties from source to target", ex);
                    }
                }
            }
        }
    }
}
