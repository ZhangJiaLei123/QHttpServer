package com.qhttpserver;

import com.blxt.httpserver.AutoControllerRegistry;
import com.blxt.httpserver.QHttpServer;
import com.blxt.httpserver.ControllerScan;

import java.io.IOException;

/**
 * @Author: Zhang.Jialei
 * @Date: 2020/9/24 11:42
 */
public class QHttpServerTest {

    public static void main(String[] args) throws IOException {

        String appPath = "/mqtt";

        // 服务端口初始化
        QHttpServer.newInstance(appPath, 8089, 0);
        // 路由器初始化
        AutoControllerRegistry.init(appPath);

        // 路由方式1. 自动扫描
        ControllerScan.autoRegistry(QHttpServerTest.class, "com.qhttpserver.test");

        // 路由方式2. 注册路由,半自动,支持自动实例化和单例
        //AutoControllerRegistry.registry(ControllerDefaultImp.class);

        // 启动服务
        QHttpServer.getInstance().start();
    }

}
