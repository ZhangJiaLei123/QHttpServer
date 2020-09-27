import com.blxt.httpserver.ControllerRegistry;
import com.blxt.httpserver.QHttpServer;

import java.io.IOException;

/**
 * @Author: Zhang.Jialei
 * @Date: 2020/9/24 11:42
 */
public class QHttpServerTest {

    public static void main(String[] args) throws IOException {

        QHttpServer.newInstance("/hello", 8089, 0);

//        QHttpHandler.addBind("/hello2/t1", new ControllerImp());
//        QHttpHandler.addBindByRoot("/hello", "/t1", new ControllerImp().setRootPath());
        // 添加 Controller 层
        ControllerDefaultImp controllerImp = new ControllerDefaultImp();
        controllerImp.setRootPath("hello");
        controllerImp.addMethod("/test1", "function1");
        controllerImp.addMethod("/test2", "function2");

        // 注册路由
        ControllerRegistry controllerRegistry = ControllerRegistry.getInstance();
        controllerRegistry.addBinds(controllerImp);

        // 启动服务
        QHttpServer.getInstance().start();
    }

}
