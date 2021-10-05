package com.usst.lzh.server;

import com.usst.lzh.Request;
import com.usst.lzh.ServiceDiscriptor;
import com.usst.lzh.common.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理暴露的RPC服务
 * 1.注册服务
 * 2.查找服务
 */
@Slf4j
public class ServiceManager {
    //<服务的描述，服务的具体实现>
    private Map<ServiceDiscriptor,ServiceInstance> services;

    public ServiceManager(){
        this.services = new ConcurrentHashMap<>();
    }

    /**
     *
     * @param interfaceClass 接口的class
     * @param bean 接口具体子类的对象
     * 服务的提供者设置为单例，bean
     */
    public <T> void register(Class<T> interfaceClass, T bean){
        Method[] methods = ReflectionUtils.getPublicMethods(interfaceClass);
        for (Method method : methods){
            ServiceInstance sis = new ServiceInstance(bean, method);
            ServiceDiscriptor sdp = ServiceDiscriptor.from(interfaceClass,method);

            services.put(sdp,sis);
            log.info("register service:{}{}",sdp.getClazz(),sdp.getMethod());
        }
    }

    public ServiceInstance lookup(Request request){
        ServiceDiscriptor sdt = request.getService();
        return services.get(sdt);

    }

}
