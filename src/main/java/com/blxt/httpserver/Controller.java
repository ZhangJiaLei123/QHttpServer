package com.blxt.httpserver;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.Map;

/**
 * Controller层接口
 * @Author: Zhang.Jialei
 * @Date: 2020/9/24 11:15
 */
public interface Controller {


    void handle(HttpExchange httpExchange) throws IOException;

    Controller setRootPath(String path);
    String getRootPath();
    boolean addMethod(String url, String methodName);
    Map getMethodMap();
}
