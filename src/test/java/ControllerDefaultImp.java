
import com.alibaba.fastjson.JSONObject;
import com.blxt.httpserver.ControllerDefault;
import java.util.Map;

/**
 * @Author: Zhang.Jialei
 * @Date: 2020/9/24 11:40
 */
public class ControllerDefaultImp extends ControllerDefault {

    public String function0(Map<String, Object> map){

        return JSONObject.toJSONString(map);
    }

    public String function1(Map<String, Object> map){

        return JSONObject.toJSONString(map);
    }

}
