package main.com.xuan.protocol;


import main.com.xuan.common.Invocation;
import main.com.xuan.register.LocalRegister;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HttpServerHandler {

    public void handler(HttpServletRequest req, HttpServletResponse resp) {
        // 处理请求-->处理什么请求：接口、方法、请求参数-->抽象成对象 Invocation
        try {
            Invocation invocation = (Invocation) new ObjectInputStream(req.getInputStream()).readObject();
            String interfaceName = invocation.getInterfaceName();
            // 根据对应接口拿到实现类--》本地注册
            Class implClass = LocalRegister.get(interfaceName);
            // 根据方法名和参数类型拿到方法
            Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamType());
            // 反射调用方法
            String result = (String) method.invoke(implClass.newInstance(), invocation.getParams());

            System.out.println("tomcat:" + result);
            //
            IOUtils.write(result, resp.getOutputStream());
        } catch (IOException | NoSuchMethodException | IllegalAccessException | InstantiationException |
                 InvocationTargetException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
