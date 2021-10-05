package com.usst.lzh.common.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtils {
    public static <T> T newInstance(Class<T> clazz){
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 获取某一个类的公共方法
     * @param clazz 类名
     * @return 公共方法数组
     */
    public static Method[] getPublicMethods(Class clazz){
        //先返回当前类所有的方法getDeclaredMethods(),然后从所有类中过滤出需要的类
        //把类添加到list中，最后转换成数组输出
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> pmethods = new ArrayList<>();
        for(Method m :methods){
            //判断修饰符
            if(Modifier.isPublic(m.getModifiers())){
                pmethods.add(m);
            }
        }
        return pmethods.toArray(new Method[0]);
    }

    /**
     * 调用指定对象的指定方法
     * @param obj 被调用对象
     * @param method 被调用的方法
     * @param args 方法的参数（可变参数）
     * @return 方法的返回值
     */
    public static Object invoke(Object obj, Method method, Object... args){
        try {
            //方法和对象是绑定的，如果调用静态方法，obj参数为空值
            return method.invoke(obj,args);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

}
