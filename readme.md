

## httpserver 自动工具
* 实现httpserver 
* 实现url自动路由到Controller层 
* 没有其他框架依赖,原生类 

## 使用 
* 快速使用 
``` java 
    String appPath = "/mqtt";
    // 1. 服务端口初始化
    QHttpServer.newInstance(appPath, 8089, 0);
    // 2. 路由器初始化
    AutoControllerRegistry.init(appPath);
    // 3. 注册路由,半自动,支持自动实例化和单例
    AutoControllerRegistry.registry(ControllerDefaultImp.class);
    // 4. 启动服务
    QHttpServer.getInstance().start();
``` 

* 注解路由,类似Springboot的使用方式,目前实现了RequestMapping、PostMapping、GetMapping，参数目前只支持固定参数<Map> 
``` java
@RequestMapping("hello")
public class ControllerDefaultImp
{
    static ControllerDefaultImp Instance = new ControllerDefaultImp();

    public static ControllerDefaultImp getInstance(){
        return Instance;
    }

    @PostMapping("/test0")
    public String function0(Map<String, Object> map){

        return JSONObject.toJSONString(map);
    }

    @GetMapping("test1")
    public String function1(Map<String, Object> map){

        return JSONObject.toJSONString(map);
    }

}

```