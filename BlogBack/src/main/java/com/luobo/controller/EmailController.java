package com.luobo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luobo.common.dto.MailDto;
import com.luobo.common.lang.Result;
import com.luobo.entity.User;
import com.luobo.service.MailService;
import com.luobo.service.UserService;
import com.luobo.util.StringRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.Date;

/**
 * @ClassName EmailConTroller
 * @Description Email controller层
 * @Author davidmorgan
 * @Date 2020/10/10 10:53
 * @Version 1.0
 **/
@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    MailService mailService;

    @Resource
    private TemplateEngine templateEngine;

    @Value("${blog-font-url}")
    String fontUrl;

    @Autowired
    UserService userService;


    /**
     * @title sendSimpleMail
     * @description 发送简单邮件
     * @author davidmorgan
     * @param: mailDto
     * @updateTime 2020/10/10 11:13
     */
    @CrossOrigin
    @PostMapping("/simple")
    public void sendSimpleMail(@Validated @RequestBody MailDto mailDto){
        mailService.sendSimpleMail(mailDto.getToMail(),mailDto.getSubject(),mailDto.getContent());
    }

    @PostMapping("/html")
    public Result sendHtmlmail(@Validated @RequestBody MailDto mailDto) {
        try {
            mailService.sendHtmlMail(mailDto.getToMail(),mailDto.getSubject(),mailDto.getContent());
            return Result.succ(null);
        } catch (MessagingException e) {
            e.printStackTrace();
            return Result.fail("发送失败");
        }
    }

    /**
     * @title sendVerifyTemplateMail
     * @description 发送HTML的邮件用来作为验证码的实现
     * @author davidmorgan
     * @param: mailDto
     * @updateTime 2020/11/26 11:05
     * @return: com.luobo.common.lang.Result
     */
    @PostMapping("/verifytemplate")
    public Result sendVerifyTemplateMail(@Validated @RequestBody MailDto mailDto)  {
        Context context = new Context();
        String random = StringRandom.getStringRandom(6);
        String strUrl = fontUrl + "/#/verifyaccount?name="+mailDto.getToMail()+"&code="+random;
        context.setVariable("username",mailDto.getUsername());
        context.setVariable("url",strUrl);
        context.setVariable("today", new Date());
        context.setVariable("fontUrl",fontUrl);
        String emailContent = templateEngine.process("VerifyTemplate", context);
        try {
            mailService.sendHtmlMail(mailDto.getToMail(),mailDto.getSubject(),emailContent);
            //调用radis将验证码存入其中，方便拿前台的验证码校验
            Jedis jedis = new Jedis("localhost");
            jedis.set(mailDto.getToMail(),random);
            //设置验证码过期时间 过期时间为一周
            jedis.expire(mailDto.getToMail(),604800);
            jedis.close();

            return Result.succ(mailDto.getToMail());
        } catch (MessagingException e) {
            e.printStackTrace();
            return Result.fail("发送失败");
        }

    }

    /**
     * @title verifyCode
     * @description 验证用户提交的验证码
     * @author davidmorgan
     * @param: mailDto
     * @updateTime 2020/11/26 11:06
     * @return: com.luobo.common.lang.Result
     */
    @PostMapping("/verifycode")
    public Result verifyCode(@Validated @RequestBody MailDto mailDto){
        String name = mailDto.getToMail();
        String code = mailDto.getVerification();

        Jedis jedis = new Jedis("localhost");
        String str = jedis.get(name);

        if(str == null){
            jedis.close();
            return Result.fail("验证码过期");
        }else if(code.equals(str)){
            User user = userService.getOne(new QueryWrapper<User>().eq("email",mailDto.getToMail()));
            if(0 == user.getStatus()){
                user.setStatus(1);
            }
            userService.saveOrUpdate(user);
            jedis.del(name);
            jedis.close();
            return Result.succ(name);
        }else{
            return Result.fail("验证码错误");
        }
    }


}
