package com.chenshuyusc.httpProxyServer;

public class Main {
    // 我自己在本地浏览器上设置的代理服务器端口号
    private static final int port = 8999;

    public static void main(String[] args) {

        HttpProxy httpProxy = new HttpProxy();
        try {
            // 应当先设置好代理服务器的端口号，再开始启动代理服务器
            httpProxy.setPort(port);
            httpProxy.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
