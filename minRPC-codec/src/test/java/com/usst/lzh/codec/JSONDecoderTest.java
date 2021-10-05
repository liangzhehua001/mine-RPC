package com.usst.lzh.codec;

import junit.framework.TestCase;

public class JSONDecoderTest extends TestCase {

    public void testDecode() {
        Encoder encoder = new JSONEncoder();
        Decoder decoder = new JSONDecoder();

        TestBean bean3 = new TestBean();
        bean3.setName("ss");
        bean3.setAge(18);
        byte[] encode = encoder.encode(bean3);

        TestBean bean4 = decoder.decode(encode, TestBean.class);
        assertEquals(bean3.getName(),bean4.getName());



    }
}