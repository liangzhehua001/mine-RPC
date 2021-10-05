package com.usst.lzh.example;


import com.usst.lzh.server.RpcServer;
import com.usst.lzh.server.RpcServerConfig;

public class Server {
    public static void main(String[] args) {
        RpcServer server = new RpcServer(new RpcServerConfig());
        server.register(CalcService.class,new CalcServiceImpl());
        server.start();
    }


}
