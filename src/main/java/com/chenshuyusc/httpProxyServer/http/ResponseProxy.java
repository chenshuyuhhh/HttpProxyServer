package com.chenshuyusc.httpProxyServer.http;

/**
 * 根据 server 的 http response 报文，获得传送给 client 的 http response 报文
 */
public class ResponseProxy {

    public static String getResponse(String responseServer){
        String url = responseServer.split(" ")[1];
        String host = responseServer.split("Host: ")[1].split("\r\n")[0];
        return null;
    }
}
