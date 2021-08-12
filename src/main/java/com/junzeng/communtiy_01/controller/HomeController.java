package com.junzeng.communtiy_01.controller;

import com.junzeng.communtiy_01.dao.DiscussPostMapper;
import com.junzeng.communtiy_01.dao.UserMapper;
import com.junzeng.communtiy_01.entity.DiscussPost;
import com.junzeng.communtiy_01.entity.Page;
import com.junzeng.communtiy_01.entity.User;
import com.junzeng.communtiy_01.service.DiscussPostService;
import com.junzeng.communtiy_01.service.LikeService;
import com.junzeng.communtiy_01.service.UserService;
import com.junzeng.communtiy_01.util.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController implements CommunityConstant {

    @Autowired
    private DiscussPostService discussPostService;


    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    @GetMapping("/")
    public String root(){
        return "forward:/index";
    }

    @RequestMapping(path="/index",method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page
    , @RequestParam(name = "orderMode", defaultValue = "0") int orderMode){
        //方法调用前，SpringMVC会自动实例化Model和Page，并将Page注入Model
        //所以，在thymeleaf中可以直接访问Page对象中的数据
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index?orderMode=" + orderMode);

        List<DiscussPost> list = discussPostService.findDiscussPosts(0,page.getOffset(),page.getLimit(),orderMode);
        List<Map<String,Object>> discusspost=new ArrayList<>();
        for(DiscussPost post:list){
            Map<String,Object> map=new HashMap<>();
            map.put("post",post);
            User user = userService.findUserById(post.getUserId());
            map.put("user",user);

            long likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_POST, post.getId());
            map.put("likeCount",likeCount);

            discusspost.add(map);
        }
        model.addAttribute("discussPosts",discusspost);
        return "/index";
    }

    @GetMapping("/error")
    public String getErrorPage() {
        return "/error/500";
    }

    @GetMapping("/denied")
    public String getDeniedPage() {
        return "/error/404";
    }

}
