package com.qhttpserver.test;

import com.alibaba.fastjson.JSONObject;
import com.blxt.httpserver.inter.*;

import java.util.Map;

/**
 * @Author: Zhang.Jialei
 * @Date: 2020/9/24 11:40
 */
@RestController
@RequestMapping("hello")
@InstanceMap("getInstance2")
public class HelloController
{
    static HelloController Instance = new HelloController();

    public static HelloController getInstance2(){
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
