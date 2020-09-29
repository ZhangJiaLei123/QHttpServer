package com.blxt.httpserver;

import com.blxt.httpserver.inter.GetMapping;
import com.blxt.httpserver.inter.PostMapping;
import com.blxt.httpserver.inter.RequestMapping;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Zhang.Jialei
 * @Date: 2020/9/29 9:11
 */
public class AutoControllerRegistry {

    static AutoControllerRegistry instance;

    static String appPath="";
    /** Controller层路由表 */
    static Map<String, ControllerMap> urlMap = new HashMap<String, ControllerMap>();

    static {
        instance = new AutoControllerRegistry();
    }

    public static AutoControllerRegistry getInstance(){
        return instance;
    }

    public static void init(String appPath){
        AutoControllerRegistry.appPath = getPath(appPath);
    }

    public static void registry(Class<?> bean){
        String modelPath = "";
        // 从类注解Configuration中获取值,判断是否是自定义的配置文件路径
        RequestMapping anno =  bean.getAnnotation(RequestMapping.class);

        if(anno != null){
            modelPath = getPath(anno.value());
        }

        //java反射机制获取所有方法名
        Method[] declaredMethods = bean.getDeclaredMethods();

        // 判断是否是单例类
        String creator = null;
        try {
            bean.getMethod("getInstance", bean);
            creator = "getInstance";
        } catch (NoSuchMethodException e) {
            creator = bean.getName();
        }

        //遍历循环方法并获取对应的注解名称
        for (Method declaredMethod : declaredMethods) {

            if("getInstance".equals(declaredMethod.getName())){
                creator = "getInstance";
            }

            PostMapping methodAnno = declaredMethod.getAnnotation(PostMapping.class);
            GetMapping methodAnno2 = declaredMethod.getAnnotation(GetMapping.class);
            String path = null;
            if(methodAnno != null){
                path = getPath(methodAnno.value());
            }
            else if(methodAnno2 != null){
                path = getPath(methodAnno2.value());
            }

            if (path == null){
                continue;
            }

            ControllerMap controllerMap = new ControllerMap(bean, declaredMethod.getName(), new String[]{}, creator);
            // 注册路由
            urlMap.put(appPath + modelPath + path , controllerMap);
            // 根据对象获取注解值
            System.out.println("PostMapping:" + appPath +  modelPath + path + "," + controllerMap.toString());

        }
    }

    private AutoControllerRegistry(){};

    /**
     * 获取路由注册器
     * @return
     */
    public Map<String, ControllerMap> getUrlMap() {
        return urlMap;
    }


    /**
     * 路径处理
     *
     * @param path
     * @return
     */
    private static String getPath(String path) {

        if(path == null){
            path = "";
        }

        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        if (path.equals("/")) {
            path = "";
        }
        return path;
    }

}
