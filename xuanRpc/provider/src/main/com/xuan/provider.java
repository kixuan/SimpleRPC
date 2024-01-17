package main.com.xuan;


import main.com.xuan.protocol.HttpServer;
import main.com.xuan.register.LocalRegister;

/**
 * @author kixuan
 * @version 1.0
 */
public class provider {
    public static void main(String[] args) {

        // 本地注册
        LocalRegister.regist(HelloService.class.getName(), HelloServiceImpl.class);


        // 启动tomcat，传入hostname和port
        HttpServer httpServer = new HttpServer();
        httpServer.start("localhost", 8080);
    }
}
