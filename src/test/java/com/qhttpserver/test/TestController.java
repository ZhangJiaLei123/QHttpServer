package com.qhttpserver.test;

import com.alibaba.fastjson.JSONObject;
import com.blxt.httpserver.inter.*;

import java.util.Map;

/**
 * @Author: Zhang.Jialei
 * @Date: 2020/9/24 11:40
 */
@RestController
@RequestMapping("test")
@InstanceMap("getInstance2")
public class TestController
{
    static TestController Instance = new TestController();

    public static TestController getInstance2(){
        return Instance;
    }

    @PostMapping("/test3")
    public String function0(Map<String, Object> map){

        return JSONObject.toJSONString(map);
    }

    @GetMapping("test4")
    public String function1(Map<String, Object> map){

        return JSONObject.toJSONString(map);
    }

}
