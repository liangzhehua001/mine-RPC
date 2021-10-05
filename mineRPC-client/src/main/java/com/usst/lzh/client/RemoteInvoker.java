package com.usst.lzh.client;

import com.usst.lzh.Request;
import com.usst.lzh.Response;
import com.usst.lzh.ServiceDiscriptor;
import com.usst.lzh.codec.Decoder;
import com.usst.lzh.codec.Encoder;
import com.usst.lzh.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 调用远程服务的代理类
 */
@Slf4j
public class RemoteInvoker implements InvocationHandler {
    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    RemoteInvoker(
            Class clazz,
            Encoder encoder,
            Decoder decoder,
            TransportSelector selector){
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.selector = selector;


    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
        request.setService(ServiceDiscriptor.from(clazz,method));
        request.setParameters(args);

        Response response = invokeRemote(request);
        if(response == null || response.getCode()!=0){
            throw new IllegalStateException("fail to invoke remote" + response);
        }
        return response.getData();
    }

    private Response invokeRemote(Request request) {
        Response resp = null;
        TransportClient client = null;
        try {
            client = selector.select();
            byte[] outBytes = encoder.encode(request);
            InputStream revice = client.write(new ByteArrayInputStream(outBytes));
            byte[] inBytes = IOUtils.readFully(revice, revice.available());
            resp = decoder.decode(inBytes, Response.class);
        }catch (IOException e){
            log.warn(e.getMessage(),e);
            resp = new Response();
            resp.setCode(1);
            resp.setMessage("RpcClient got error:"
            + e.getClass()
            +" : " + e.getMessage());
        } finally {
            if(client != null){
                selector.release(client);
            }
        }
        return resp;

    }
}