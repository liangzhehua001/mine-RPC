package com.usst.lzh;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Peer {
    //主机地址
    private String host;
    //端口号
    private int port;
}
