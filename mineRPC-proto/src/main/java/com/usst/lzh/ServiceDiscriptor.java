package com.usst.lzh;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;

/*
 *表示服务
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDiscriptor {
    //服务的类名
    private String clazz;
    //调用的方法
    private String method;
    //服务返回值类型
    private String returnType;
    //参数类型
    private String[] parameterTypes;

    public static ServiceDiscriptor from(Class clazz, Method method){
        ServiceDiscriptor sdp = new ServiceDiscriptor();
        sdp.setClazz(clazz.getName());
        sdp.setMethod(method.getName());
        sdp.setReturnType(method.getReturnType().getName());

        Class[] parameterClasses =method.getParameterTypes();
        String[] parameterTypes = new String[parameterClasses.length];
        for(int i=0;i< parameterClasses.length;i++){
            parameterTypes[i] = parameterClasses[i].getName();
        }
        sdp.setParameterTypes(parameterTypes);
        return sdp;
    }

    @Override
    public int hashCode() {

        return toString().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o ) return true;
        if(o==null|| getClass()!=o.getClass()) return false;

        ServiceDiscriptor that  = (ServiceDiscriptor) o;
        return this.toString().equals(that.toString());

    }

    @Override
    public String toString() {

        return "class:" + clazz
                + ";method:" + method
                + ";ReturnType" + returnType
                + ";parameterTypes:" + Arrays.toString(parameterTypes);
    }
}


