package main.com.xuan;


import main.com.xuan.common.URL;
import main.com.xuan.protocol.HttpServer;
import main.com.xuan.register.LocalRegister;
import main.com.xuan.register.RemoteMapRegister;

/**
 * @author kixuan
 * @version 1.0
 */
public class provider {
    public static void main(String[] args) {

        // 本地注册
        LocalRegister.regist(HelloService.class.getName(), HelloServiceImpl.class);

        // 注册中心注册【服务注册】
        URL url = new URL("localhost", 8080);
        RemoteMapRegister.register(HelloService.class.getName(), url);

        // 启动tomcat，传入hostname和port
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostname(), url.getPort());
    }
}
