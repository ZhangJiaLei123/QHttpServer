package com.blxt.httpserver;

import com.blxt.httpserver.inter.Controller;
import com.blxt.httpserver.inter.RestController;
import com.blxt.httpserver.util.JarUtil;
import com.blxt.httpserver.util.PackageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Controller扫描工具
 * @Author: Zhang.Jialei
 * @Date: 2020/9/29 15:20
 */
public class ControllerScan {
    public static Logger logger =  LoggerFactory.getLogger(ControllerScan.class);

    /**
     * 要扫描的包
     * @param packageName
     */
    public static void autoRegistry(Class<?> rootClass, String packageName){
        /* jar包的真实路径 */
        String jarpath = JarUtil.getPathReal(rootClass);
        logger.debug("jar真实路径{}", jarpath);
        jarpath = jarpath.replace("/", ".");

        List<String> classNames = PackageUtil.getClassName(packageName, true);
        if (classNames != null) {
            for (String className : classNames) {
                // 这里要对类的真实路径做处理
                if(className.startsWith(jarpath)) {
                    className = className.substring(jarpath.length());
                }

                Class<?> aClass = null;
                try {
                    aClass = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    aClass = null;
                    e.printStackTrace();
                }
                if(aClass == null){
                    continue;
                }
                // 判断是否是Controller层
                RestController anno = aClass.getAnnotation(RestController.class);
                Controller anno2 = aClass.getAnnotation(Controller.class);
                if(anno != null || anno2!= null){
                    AutoControllerRegistry.registry(aClass);
                }
            }
        }

    }

}
