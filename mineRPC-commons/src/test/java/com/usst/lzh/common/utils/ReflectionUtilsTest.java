package com.usst.lzh.common.utils;

import junit.framework.TestCase;
import org.junit.Test;

import java.lang.reflect.Method;

public class ReflectionUtilsTest extends TestCase {
    @Test
    public void testNewInstance() {
        TestClass t = ReflectionUtils.newInstance(TestClass.class);
        assertNotNull(t);
    }
    @Test
    public void testGetMethods() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        assertEquals(1,methods.length);

        String mname = methods[0].getName();
        assertEquals("b",mname);

    }
    @Test
    public void testInvoke() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        Method b = methods[0];
        TestClass t = new TestClass();
        Object x = ReflectionUtils.invoke(t,b);
        assertEquals("b",x);
    }
}