package com.chenshuyusc.httpProxyServer.HttpServer;

import com.chenshuyusc.httpProxyServer.Entity.Request;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ProxyServer {
    private final String CLIENT = "CLIENT";
    private final String SERVER = "SERVER";


    // 为端口号开一个线程监听
    public void requestFromClient(int port) throws Exception {

        ServerSocket serverSocket = new ServerSocket(port);

        // (1) 接收新连接线程
        new Thread(() -> {
            while (true) {
                try {
                    // (1) 阻塞方法获取新的连接
                    Socket socket = serverSocket.accept();

                    // (2) 每一个新的连接都创建一个线程，负责读取数据
                    asServer(socket);
                } catch (IOException e) {
                }

            }
        }).start();
    }

    private void asServer(Socket socket) {
        new Thread(() -> {
            try {
                // in --- 负责读取来自客户端的请求
                InputStream in = socket.getInputStream();

                // out --- 负责将来自服务器的响应 http 转发给 服务器
                OutputStream out = socket.getOutputStream();

                // 将客户端的请求信息接收下来，并打印出来
                String httpRequest = readIn(in, SERVER);

                // 代理服务器作为远程服务器的客户端开始工作
                String httpReponse = asClient(httpRequest);

                out.write(httpReponse.getBytes());
                out.flush();

            } catch (IOException e) {
            }
        }).start();
    }

    /**
     * 根据client 的 http request，将报文转发给 server，并接收来自server的响应
     *
     * @param msg
     * @throws IOException
     */
    private String asClient(String msg) throws IOException {
        // 获得 http request 实体
        Request request = new Request();
        request.getRequest(msg);

        // 根据 http request 的信息和目的服务器建立 socket 链接
        Socket socket = new Socket(request.getHost(), 8989);


        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();

        // 注意这里不能直接用客户端的msg，应该用 request 处理过后的 msg
        System.out.println(" 🌘 http 代理服务器将客户端的请求转发到目的服务器\n" + request.getMsg() + "\n");
        outputStream.write(request.getMsg().getBytes());
        outputStream.flush();

        String httpResponse = readIn(inputStream, CLIENT);
        System.out.println(" 🌕 http 代理服务器将服务器的响应返回给客户端\n" + httpResponse + "\n");
        return httpResponse;
    }

    /**
     * 读取 client 的 http request 信息
     *
     * @param in
     * @return
     * @throws IOException
     */
    private String readIn(InputStream in, String position) throws IOException {
        int count = 0;
        while (count == 0) {
            count = in.available();
        }
        byte[] bytes = new byte[count];
        in.read(bytes);
        String msg = new String(bytes);
        if (position == CLIENT) {
            System.out.println(" 🌗 http 代理服务器收到服务器的响应报文：\n" + msg + "\n");
        } else {
            System.out.println(" 🌑 http 代理服务器收到客户端的请求报文：\n" + msg + "\n");
        }
        return msg;
    }
}