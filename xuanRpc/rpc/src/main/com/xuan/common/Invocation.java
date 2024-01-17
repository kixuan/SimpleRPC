package main.com.xuan.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 传输对象
 * 封装了接口名、方法名、参数、参数类型
 */
@Data
@AllArgsConstructor

public class Invocation implements Serializable {

    private String interfaceName;
    private String methodName;
    private Class[] paramType;
    private Object[] params;
}
