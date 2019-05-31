package com.chenshuyusc.httpProxyServer.Simulate;

import java.io.IOException;
import java.net.ServerSocket;

public class SimulateServer {
    private static int serverPort = 9999;
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(serverPort);
            System.out.println("本地服务器开始启动");

            // 全部交给 handler 来处理
//            Handler handler = new Handler(serverSocket);
//            handler.handle();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("接收端出现异常");
        } finally {
            if (serverSocket != null)
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("接收端 finally 出现异常");
                }
        }
    }
}
