package main.com.xuan;

/**
 * @author kixuan
 * @version 1.0
 */
public class consumer {
    public static void main(String[] args) {
        HelloService helloService = ?;
        String result = helloService.sayHello("xuanzai");
        System.out.println(result);
    }
}
