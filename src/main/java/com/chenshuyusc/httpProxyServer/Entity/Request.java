package com.chenshuyusc.httpProxyServer.Entity;

public class Request {
    private String url;
    private String host;
    private int port;
    private String msg;

    public Request() {

    }

    public Request(String url, String host, int port) {
        this.url = url;
        this.host = host;
        this.port = port;
    }

    public Request(String url, String host, int port, String msg) {
        this.url = url;
        this.host = host;
        this.port = port;
        this.msg = msg;
    }

    public String getUrl() {
        return url;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getMsg() {
        return msg;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void getRequest(String request) {

        // 按行切割
        String[] lines = request.split("\r\n");

        // 获得 host 和 port
        String baseurl = lines[1].split("Host: ")[1];
        String host = baseurl.split(":")[0];
        String port = baseurl.split(":")[1];

        String url = lines[0].split(" ")[1];

        if (request.split("Referer: ").length > 1) {
            // 除了 baseurl 外还有路径
            String temp = request.split("Referer: ")[1].split("\r\n")[0];
            url = temp.split(baseurl)[1];
            // 将 url 替换为 ref
            request = updateUrl(request, url);
        } else if (url.split("http").length > 1) {
            url = url.split(baseurl)[1];
            request = updateUrl(request, url);
        }
        // url 去掉/
        url = url.substring(1);

        if (host.split("www.")[1].split(".com")[0].equals("localhost")) {
            host = "localhost";
        }

        this.msg = request;
        this.host = host;
        this.port = Integer.valueOf(port);
        this.url = url;
    }

    private String updateUrl(String request, String url) {
        // 修改第一行
        String result1 = request.split(" ")[0];
        StringBuffer result = new StringBuffer(result1);
        result.append(" ").append(url).append(" ").append("\r\n");

        // 将后面所有行都添加回去
        String[] lines = request.split("\r\n");
        for (int i = 1;i<lines.length;i++){
            result.append(lines[i]).append("\r\n");
        }
        return result.toString();
    }
}
