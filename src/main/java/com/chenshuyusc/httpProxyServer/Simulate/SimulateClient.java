package com.chenshuyusc.httpProxyServer.Simulate;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 为了测试写的模拟客户端，但是这个客户端没有配置 http 代理，不能使用。还是在浏览器上测试吧
 */
public class SimulateClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8888);
            OutputStream out = socket.getOutputStream();
            out.write(getHttpRequest(""));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] getHttpRequest(String url) {
        String msg = "GET " +
                "/" + url +
                " HTTP/1.1\n" +
                "Host: localhost:8888\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" +
                "Upgrade-Insecure-Requests: 1\n" +
                "Cookie: Pycharm-9aa39fb5=23f4c682-ba41-4077-8456-46df43899c04; _pk_id.11.1fff=ce88320ebe286caf.1554982419.1.1554982921.1554982419.; Idea-8ad07999=8b790fe0-9cfb-4ec6-aeea-574ae421aad0; Webstorm-9cd8e5e0=6b355238-fd8c-42e4-81a7-5f30e87eaf2c\n" +
                "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.0 Safari/605.1.15\n" +
                "Accept-Language: zh-cn\n" +
                "Accept-Encoding: gzip, deflate\n" +
                "Connection: keep-alive";
        return msg.getBytes();
    }
}
