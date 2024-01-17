package main.com.xuan;


import main.com.xuan.protocol.HttpServer;

/**
 * @author kixuan
 * @version 1.0
 */
public class provider {
    public static void main(String[] args) {

        // 启动tomcat，传入hostname和port
        HttpServer httpServer = new HttpServer();
        httpServer.start("localhost", 8080);
    }
}
