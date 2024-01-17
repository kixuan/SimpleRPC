package main.com.xuan.protocol;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 处理请求，用 HttpServerHandler 方便扩展，专门处理请求
        // 过滤器/责任链模式，可以通过不同handler处理不同的请求
        new HttpServerHandler().handler(req, resp);
    }
}