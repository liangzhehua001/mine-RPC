package com.usst.lzh;

import lombok.Data;

/*
表示一个RPC的返回
 */
@Data
public class Response {
    //返回的编码，0--成功，非0--失败
    private int code = 0;
    //错误信息
    private String message = "success";
    //返回的数据
    private Object data;

}
