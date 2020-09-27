package com.blxt.httpserver;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller 层注路由 册器
 * @Author: Zhang.Jialei
 * @Date: 2020/9/24 16:44
 */
public class ControllerRegistry {

    static ControllerRegistry instance;

    /** Controller层路由表 */
    static Map<String, Controller> urlMap = new HashMap<String, Controller>();

    static {
        instance = new ControllerRegistry();
    }

    public static ControllerRegistry getInstance(){
        return instance;
    }

    private ControllerRegistry(){};

    /**
     * 获取路由注册器
     * @return
     */
    public Map<String, Controller> getUrlMap() {
        return urlMap;
    }

    /**
     * 注册Controller层。绑定路由。
     *
     * @see {@link Controller }
     * @param controlle
     */
    public void addBinds(Controller controlle){
        Map<String , String> controlleMaps = controlle.getMethodMap();
        /* 遍历 绑定 */
        for(String key : controlleMaps.keySet()){
            addBind(key, controlle);
        }
    }

    /**
     * Controller 层绑定
     * @param path
     * @param controller
     * @return
     */
    private boolean addBind(String path, Controller controller){
        urlMap.put(path, controller);
       // System.out.println("绑定:" +  path);
        return true;
    }

}
