
import com.alibaba.fastjson.JSONObject;
import com.blxt.httpserver.inter.GetMapping;
import com.blxt.httpserver.inter.PostMapping;
import com.blxt.httpserver.inter.RequestMapping;

import java.util.Map;

/**
 * @Author: Zhang.Jialei
 * @Date: 2020/9/24 11:40
 */
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
