package com.blxt.httpserver;

import com.alibaba.fastjson.JSONObject;
import com.blxt.utils.Converter;
import com.blxt.utils.check.CheckUtils;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller 层默认路由,实现 {@link Controller} 接口
 *
 * @Author: Zhang.Jialei
 * @Date: 2020/9/24 17:42
 */
public abstract class ControllerDefault implements Controller {

    /**
     * 根路径
     */
    String rootpath = "";

    /**
     * 路由注册器
     */
    Map<String, String> methodMap = new HashMap<String, String>();


    /**
     * 默认的url路由
     *
     * @param httpExchange
     * @throws IOException
     */
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        URI url = httpExchange.getRequestURI();

        // 获取参数
        String s1 = url.getQuery();
        JSONObject jsonObject = Converter.urlParam2Json(s1);
        Map<String, Object> map = Converter.toMap(jsonObject);

        // 反射方法
        String response = null;
        try {
            String methodname = methodMap.get(url.getPath());
            Method m2 = getClass().getDeclaredMethod(methodname, Map.class);
            response = (String) m2.invoke(this, map);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 结果返回
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();

    }

    /**
     * 设置根url
     *
     * @param path
     * @return
     */
    @Override
    public Controller setRootPath(String path) {
        CheckUtils.objectCheckNull(path, "根url不得为NULL", "1001", "");
        if (path.equals("/")) {
            path = "";
        }
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        this.rootpath = path;
        return this;
    }

    /**
     * 获取路由
     *
     * @return
     */
    @Override
    public String getRootPath() {

        return rootpath;
    }

    /**
     * 添加方法绑定
     *
     * @param url        路径
     * @param methodName 方法名
     * @return
     */
    @Override
    public boolean addMethod(String url, String methodName) {
        CheckUtils.objectCheckNull(url, "url不得为NULL", "1001", "");
        if (!url.startsWith("/")) {
            url = "/" + url;
        }
        methodMap.put(rootpath + url, methodName);
        return true;
    }

    /**
     * 获取路由注册器
     *
     * @return
     */
    @Override
    public Map getMethodMap() {
        return methodMap;
    }

}
