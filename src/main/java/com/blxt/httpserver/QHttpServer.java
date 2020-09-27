package com.blxt.httpserver;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;

/**
 * @Author: Zhang.Jialei
 * @Date: 2020/9/24 10:52
 */
public class QHttpServer {

    static QHttpServer instance = null;

    public static QHttpServer getInstance(){
        return instance;
    }

    public static QHttpServer newInstance(String path, int port, int backlog) throws IOException {
        if(instance != null){
            return instance;
        }

        instance = new QHttpServer(path, port, backlog);

        return instance;
    }

    HttpServer httpServer;
    int port = 8080;
    int backlog = 0;
    String path="/";
    Executor executor = null;

    private QHttpServer( String path, int port, int backlog) throws IOException {
        this.port = port;
        this.backlog = backlog;
        this.path = path;

        bind();
    }

    private void bind() throws IOException {
        httpServer = HttpServer.create(new InetSocketAddress(port), backlog);
        httpServer.createContext(path, new QHttpHandler());
        httpServer.setExecutor(executor);
    }


    public void start(){
        httpServer.start();
    }


}
