package main.com.xuan;

import main.com.xuan.common.Invocation;
import main.com.xuan.protocol.HttpClient;

/**
 * @author kixuan
 * @version 1.0
 */
public class consumer {
    public static void main(String[] args) {
        // HelloService helloService = ?;
        // String result = helloService.sayHello("xuanzai");
        // System.out.println(result);

        // 生成invocation对象
        Invocation invocation = new Invocation(HelloService.class.getName(), "sayHello",
                new Class[]{String.class}, new Object[]{"xuanzai"});

        // 利用send方法进行发送对象
        HttpClient httpClient = new HttpClient();
        String result = httpClient.send("localhost", 8080, invocation);
        System.out.println(result);
    }
}
