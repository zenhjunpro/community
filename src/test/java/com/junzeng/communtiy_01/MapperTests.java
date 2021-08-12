package com.junzeng.communtiy_01;

import com.junzeng.communtiy_01.dao.DiscussPostMapper;
import com.junzeng.communtiy_01.dao.MessageMapper;
import com.junzeng.communtiy_01.dao.UserMapper;
import com.junzeng.communtiy_01.entity.DiscussPost;
import com.junzeng.communtiy_01.entity.Message;
import com.junzeng.communtiy_01.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommuntiyApplication.class)
public class MapperTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DiscussPostMapper discussPostMapper;
    @Autowired
    private MessageMapper messageMapper;

    @Test
    public void test1(){
        User user= userMapper.selectById(101);
        System.out.println(user);

        user= userMapper.selectByEmail("nowcoder1@sina.com");
        System.out.println(user);

        user=userMapper.selectByName("liubei");
        System.out.println(user);
    }

    @Test
    public void test_insert(){
        User user = new User();
        user.setUsername("test");
        user.setPassword("123456");
        user.setSalt("abc");
        user.setEmail("test@qq.com");
        user.setHeaderUrl("http://www.nowcoder.com/101.png");
        user.setCreateTime(new Date());

        int rows = userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }

    @Test
    public void test_update(){

        int rows=userMapper.updateStatus(150,1);
        System.out.println(rows);
        System.out.println(userMapper.selectById(150));

        rows = userMapper.updateHeader(150, "http://www.nowcoder.com/102.png");
        System.out.println(rows);

        rows = userMapper.updatePassword(150, "hello");
        System.out.println(rows);
    }

    @Test
    public void test_selectDiscussPost(){
        List<DiscussPost> list=discussPostMapper.selectDiscussPosts(149,1,10,0);
        for(DiscussPost post:list){
            System.out.println(post);
        }

        int rows=discussPostMapper.selectDiscussPostRows(0);
        System.out.println(rows);
        rows=discussPostMapper.selectDiscussPostRows(111);
        System.out.println(rows);

    }

    @Test
    public void test_Message(){
        List<Message> messages = messageMapper.selectConversations(111, 0, 20);
        for(Message message:messages){
            System.out.println(message);
        }
        int count = messageMapper.selectConversationCount(111);
        System.out.println(count);

        messages = messageMapper.selectLetters("111_112",0,10);
        for(Message message:messages){
            System.out.println(message);
        }
        count = messageMapper.selectLetterCount("111_112");
        System.out.println(count);

        count = messageMapper.selectLetterUnreadCount(131,"111_131");
        System.out.println(count);
    }
}
