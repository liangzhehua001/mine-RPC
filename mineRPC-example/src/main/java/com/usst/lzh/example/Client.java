package com.usst.lzh.example;

import com.usst.lzh.client.RpcClient;

public class Client {
    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        CalcService service = client.getProxy(CalcService.class);

        int r1 = service.add(0,1);
        int r2 = service.minus(3,2);
        System.out.println(r1);
        System.out.println(r2);


    }
}
