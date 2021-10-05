package com.usst.lzh.client;

import com.usst.lzh.Peer;
import com.usst.lzh.codec.Decoder;
import com.usst.lzh.codec.Encoder;
import com.usst.lzh.codec.JSONDecoder;
import com.usst.lzh.codec.JSONEncoder;
import com.usst.lzh.transport.HTTPTransportClient;
import com.usst.lzh.transport.TransportClient;
import lombok.Data;
import java.util.Arrays;
import java.util.List;
@Data
public class RpcClientConfig {
    private Class<? extends TransportClient> transportClass = HTTPTransportClient.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;
    private int connectCount = 1;
    private List<Peer> servers = Arrays.asList(
            new Peer("127.0.0.1", 3000)
    );
}
