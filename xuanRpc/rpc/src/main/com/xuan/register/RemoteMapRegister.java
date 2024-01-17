package main.com.xuan.register;


import main.com.xuan.common.URL;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteMapRegister {

    // 注意这里是list，因为一个接口可能有多个实现类
    private static Map<String, List<URL>> REGISTER = new HashMap<>();


    public static void register(String interfaceName, URL url) {

        List<URL> list = REGISTER.get(interfaceName);
        if (list == null) {
            list = new ArrayList<>();

        }
        list.add(url);

        REGISTER.put(interfaceName, list);

        saveFile();
    }

    public static List<URL> get(String interfaceName) {
        REGISTER = getFile();

        List<URL> list = REGISTER.get(interfaceName);
        return list;
    }

    /**
     * 保存到文件，这样才能保证consumer和provider都能获取到注册中心的信息，共享数据，保证数据一致性和高可用性
     * consumer和provider都监听文件，一旦文件发生变化，就重新获取信息 --【数据监听机制】
     * provider挂了，consumer要及时获取信息--【心跳机制】
     * consumer每调一次方法，都存在本地缓存，提高速度 --【本地缓存】
     */
    private static void saveFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("/temp.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(REGISTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, List<URL>> getFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("/temp.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (Map<String, List<URL>>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
