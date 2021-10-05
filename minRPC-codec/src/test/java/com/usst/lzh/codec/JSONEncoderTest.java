package com.usst.lzh.codec;

import junit.framework.TestCase;
import org.junit.Test;

public class JSONEncoderTest extends TestCase {

    @Test
    public void testEncode() {
        Encoder encoder = new JSONEncoder();

        TestBean bean3 = new TestBean();
        bean3.setName("ss");
        bean3.setAge(18);
        byte[] encode = encoder.encode(bean3);

        assertNotNull(encode);


    }
}