package com.usst.lzh.codec;

public interface Decoder {
    <T> T decode(byte[] bytes, Class clazz);
}
