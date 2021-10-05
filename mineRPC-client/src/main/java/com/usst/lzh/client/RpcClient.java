package com.usst.lzh.client;

import com.usst.lzh.codec.Decoder;
import com.usst.lzh.codec.Encoder;
import com.usst.lzh.common.utils.ReflectionUtils;

import java.lang.reflect.Proxy;

public class RpcClient {
    private RpcClientConfig rpcClientConfig;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RpcClient() {
        this(new RpcClientConfig());
    }

    public RpcClient(RpcClientConfig rpcClientConfig) {
        this.rpcClientConfig = rpcClientConfig;
        this.decoder = ReflectionUtils.newInstance(this.rpcClientConfig.getDecoderClass());
        this.encoder = ReflectionUtils.newInstance(this.rpcClientConfig.getEncoderClass());
        this.selector = ReflectionUtils.newInstance(this.rpcClientConfig.getSelectorClass());

        this.selector.init(
                this.rpcClientConfig.getServers(),
                this.rpcClientConfig.getConnectCount(),
                this.rpcClientConfig.getTransportClass()
        );
    }

    public <T> T getProxy(Class<T> clazz){
        return (T) Proxy.newProxyInstance(
             getClass().getClassLoader(),
             new Class[]{clazz},
             new RemoteInvoker(clazz,encoder,decoder,selector)
        );
    }
}
