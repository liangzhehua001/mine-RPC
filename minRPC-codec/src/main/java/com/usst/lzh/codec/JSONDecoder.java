package com.usst.lzh.codec;

import com.alibaba.fastjson.JSON;

public class JSONDecoder implements Decoder{

    @Override
    public <T> T decode(byte[] bytes, Class clazz) {
        return JSON.parseObject(bytes,clazz);
    }
}
