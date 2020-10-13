package com.luobo.controller;

import com.luobo.common.dto.MailDto;
import com.luobo.common.lang.Result;
import com.luobo.service.MailService;
import com.luobo.util.StringRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.mail.MessagingException;

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


    @PostMapping("/verifytemplate")
    public Result sendVerifyTemplateMail(@Validated @RequestBody MailDto mailDto)  {
        Context context = new Context();
        String random = StringRandom.getStringRandom(6);;
        context.setVariable("randomCode",random);
        String emailContent = templateEngine.process("Verify", context);
        try {
            mailService.sendHtmlMail(mailDto.getToMail(),mailDto.getSubject(),emailContent);
            //调用radis将验证码存入其中，方便拿前台的验证码校验
            Jedis jedis = new Jedis("localhost");
            jedis.set(mailDto.getToMail(),random);
            //设置验证码过期时间 过期时间为300秒
            jedis.expire(mailDto.getToMail(),300);
            jedis.close();

            return Result.succ(mailDto.getToMail());
        } catch (MessagingException e) {
            e.printStackTrace();
            return Result.fail("发送失败");
        }

    }

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
            jedis.del(name);
            jedis.close();
            return Result.succ(name);
        }else{
            return Result.fail("验证码错误");
        }
    }


}
