package com.usst.lzh.server;

import com.usst.lzh.codec.Decoder;
import com.usst.lzh.codec.Encoder;
import com.usst.lzh.codec.JSONDecoder;
import com.usst.lzh.codec.JSONEncoder;
import com.usst.lzh.transport.HTTPTransportServer;
import com.usst.lzh.transport.TransportServer;
import lombok.Data;

/**
 * Server配置
 * 1.使用的网络模块
 * 2.序列化实现
 * 3.监听的端口
 */
@Data
public class RpcServerConfig {
    private Class<? extends TransportServer> transportClass = HTTPTransportServer.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private int port = 3000;
}
