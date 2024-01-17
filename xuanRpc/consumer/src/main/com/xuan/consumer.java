package main.com.xuan;

import main.com.xuan.proxy.ProxyFactory;

/**
 * @author kixuan
 * @version 1.0
 */
public class consumer {
    public static void main(String[] args) {
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String result = helloService.sayHello("xuanzai");
        System.out.println(result);

        // // 生成invocation对象
        // Invocation invocation = new Invocation(HelloService.class.getName(), "sayHello",
        //         new Class[]{String.class}, new Object[]{"xuanzai"});
        //
        // // 利用send方法进行发送对象
        // HttpClient httpClient = new HttpClient();
        // String result = httpClient.send("localhost", 8080, invocation);
        // System.out.println(result);
    }
}
