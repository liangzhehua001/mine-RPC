package com.usst.lzh;

import lombok.Data;

/*
表示一个RPC请求
 */
@Data
public class Request {
    //服务名
    private ServiceDiscriptor service;
    //使用的参数
    private Object[] parameters;

}
