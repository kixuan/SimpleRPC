package main.com.xuan.protocol;

import main.com.xuan.common.Invocation;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 客户端发送对象
 */
public class HttpClient {

    public String send(String hostname, Integer port, Invocation invocation) {

        try {

            // 读取用户配置的服务提供者的地址和端口号
            URL url = new URL("http", hostname, port, "/");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);

            oos.writeObject(invocation);
            oos.flush();
            oos.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            return IOUtils.toString(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }
}
