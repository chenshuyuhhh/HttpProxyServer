package com.chenshuyusc.httpProxyServer;

import com.chenshuyusc.httpProxyServer.HttpServer.ProxyServer;

public class HttpProxy {
    private ProxyServer server = new ProxyServer();
    private int port;

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    /**
     * 启动应该先设置端口号
     *
     * @throws Exception
     */
    public void start() throws Exception {
        System.out.println("http 代理服务器开始了它在端口号为" + port + "的表演");
        server.requestFromClient(port);
    }
}
