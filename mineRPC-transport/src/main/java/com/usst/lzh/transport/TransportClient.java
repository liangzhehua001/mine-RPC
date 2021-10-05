package com.usst.lzh.transport;

import com.usst.lzh.Peer;

import java.io.InputStream;

public interface TransportClient {
    void connect(Peer peer);
    InputStream write(InputStream data);
    void close();

}
