package com.junzeng.communtiy_01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestUseful {
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "<em>曾俊要好好玩明白这个项目呀！</em>";
    }
}
