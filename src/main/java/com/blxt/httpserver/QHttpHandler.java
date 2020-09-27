package com.blxt.httpserver;

import com.blxt.utils.check.CheckUtils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.URI;

/**
 * http的路径分配
 * @Author: Zhang.Jialei
 * @Date: 2020/9/24 11:07
 */
public class QHttpHandler implements HttpHandler {

    public void handle(HttpExchange httpExchange) throws IOException {

        // 路径分配
        URI url =  httpExchange.getRequestURI();
        String urlPath = url.getPath();
        Controller controller = ControllerRegistry.getInstance().getUrlMap().get(urlPath);

        CheckUtils.objectCheckNull(controller, "没有找到路径", "1001", "");
        // 传递到具体实现
        controller.handle(httpExchange);

    }



}
