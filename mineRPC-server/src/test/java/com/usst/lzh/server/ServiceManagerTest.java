package com.usst.lzh.server;

import com.usst.lzh.Request;
import com.usst.lzh.ServiceDiscriptor;
import com.usst.lzh.common.utils.ReflectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ServiceManagerTest {
    ServiceManager sm;

    @Before
    public void init(){
        sm = new ServiceManager();
        TestInterface bean = new TestClass();
        sm.register(TestInterface.class, bean);
    }

    @Test
    public void register() {
        TestInterface bean1 = new TestClass();
        sm.register(TestInterface.class, bean1);

    }

    @Test
    public void lookup() {
        Method method = ReflectionUtils.getPublicMethods(TestInterface.class)[0];
        ServiceDiscriptor serviceDiscriptor = ServiceDiscriptor.from(TestInterface.class, method);

        Request request = new Request();
        request.setService(serviceDiscriptor);

        ServiceInstance sis = sm.lookup(request);
        assertNotNull(sis);

    }
}