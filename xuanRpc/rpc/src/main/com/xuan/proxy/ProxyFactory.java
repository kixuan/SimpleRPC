package main.com.xuan.proxy;

import main.com.xuan.common.Invocation;
import main.com.xuan.common.URL;
import main.com.xuan.loadbalance.LoadBalance;
import main.com.xuan.protocol.HttpClient;
import main.com.xuan.register.RemoteMapRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class ProxyFactory<T> {

    @SuppressWarnings("unchecked")
    public static <T> T getProxy(final Class interfaceClass) {
        // 代理对象：第三个参数是代理逻辑
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            // invoke方法干的就是把远程调用的过程封装成Invocation对象，然后通过网络传输给服务端，
            // 服务端接收到Invocation对象后，再通过反射调用本地的实现类，最后把结果返回给客户端
            // 这里的逻辑和之前写在consumer的逻辑是一样的
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 生成invocation对象
                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(),
                        method.getParameterTypes(), args);

                // 利用send方法进行发送对象
                HttpClient httpClient = new HttpClient();

                // 从注册中心获取服务提供者的地址列表【服务发现】
                List<URL> urls = RemoteMapRegister.get(interfaceClass.getName());

                //【负载均衡 -- 有多个URL该选择哪个】
                URL randomURL = LoadBalance.random(urls);

                // 【服务调用】
                String result = httpClient.send(randomURL.getHostname(), randomURL.getPort(), invocation);
                return result;

            }
        });
    }

}
