package com.usst.lzh.server;

import com.usst.lzh.Request;
import com.usst.lzh.common.utils.ReflectionUtils;

public class ServerInvoker {
    public Object invoke(ServiceInstance serviceInstance, Request request){
        return ReflectionUtils.invoke(
                serviceInstance.getTarget(),
                serviceInstance.getMethod(),
                request.getParameters());


    }
}
