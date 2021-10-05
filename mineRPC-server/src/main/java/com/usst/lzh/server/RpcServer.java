package com.usst.lzh.server;

import com.usst.lzh.Request;
import com.usst.lzh.Response;
import com.usst.lzh.codec.Decoder;
import com.usst.lzh.codec.Encoder;
import com.usst.lzh.common.utils.ReflectionUtils;
import com.usst.lzh.transport.RequestHandler;
import com.usst.lzh.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;


import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
public class RpcServer {
    private RpcServerConfig config;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServerInvoker serverInvoker;

    public RpcServer(RpcServerConfig config) {
        this.config = config;
        this.net = ReflectionUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(), this.handler);

        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());

        this.serviceManager = new ServiceManager();
        this.serverInvoker = new ServerInvoker();
    }

    public <T> void register(Class<T> interfaceClass, T bean){
        serviceManager.register(interfaceClass,bean);
    }

    public void start(){
        this.net.start();
    }

    public void stop(){
        this.net.stop();
    }

    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream inputStream, OutputStream outputStream) {
            Response response = new Response();
            try {
                byte[] inBytes = IOUtils.readFully(inputStream, inputStream.available());
                Request request = decoder.decode(inBytes, Request.class);
                log.info("get request:{}", request);

                ServiceInstance serviceInstance = serviceManager.lookup(request);
                Object ret = serverInvoker.invoke(serviceInstance, request);
                response.setData(ret);


            } catch (Exception e) {
                log.warn(e.getMessage(),e);
                response.setCode(1);
                response.setMessage("RpcServer got error:" + e.getClass().getName() + " : "
                        + e.getMessage());
            }finally {
                try {
                    byte[] outBytes = encoder.encode(response);
                    outputStream.write(outBytes);
                    log.info("response client");
                } catch (Exception e) {
                    log.warn(e.getMessage(),e);
                }

            }
        }
    };
}
