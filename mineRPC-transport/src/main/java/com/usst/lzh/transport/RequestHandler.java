package com.usst.lzh.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 处理网络请求
 */
public interface RequestHandler {
    void onRequest(InputStream inputStream, OutputStream outputStream);
}
