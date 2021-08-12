package com.junzeng.communtiy_01;

import com.junzeng.communtiy_01.util.MailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommuntiyApplication.class)
public class MailTests {

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testSendMail(){
        mailClient.sendMail("zengjun_mail@163.com","TEST","HELLO!我来给你发邮件了");
    }

    @Test
    public void testHtmlMail(){
        Context context=new Context();
        context.setVariable("usrname","曾俊");
        String content=templateEngine.process("/mail/demo",context);
        System.out.println(content);
        mailClient.sendMail("zengjun_mail@163.com","HTMl",content);
    }
}
