package main.com.xuan.loadbalance;

import main.com.xuan.common.URL;

import java.util.List;
import java.util.Random;

/**
 * 负载均衡
 */
public class LoadBalance {

    // 随机策略，简单实现
    public static URL random(List<URL> list) {
        Random random = new Random();
        int n = random.nextInt(list.size());
        return list.get(n);
    }
}
