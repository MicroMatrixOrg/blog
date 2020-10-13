package com.luobo.service.impl;

import com.luobo.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;


/**
 * @ClassName MailServiceImpl
 * @Description 邮件发送服务
 * @Author davidmorgan
 * @Date 2020/10/10 09:37
 * @Version 1.0
 **/

@Slf4j
@Service
public class MailServiceImpl implements MailService {
    private final Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;


    /**
     * @title 简单文本邮件
     * @description
     * @author admin
     * @param: to 邮件接收者
     * @param: subject 邮件主题
     * @param: content 邮件内容
     * @updateTime 2020/10/10 10:17
     */
    @Override
    public void sendSimpleMail(String to,String subject,String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        message.setFrom(from);

        mailSender.send(message);
    }

    /**
     * @title HTML 文本邮件
     * @description
     * @author admin
     * @param: to 邮件接收者
     * @param: subject 邮件主题
     * @param: content 邮件内容
     * @updateTime 2020/10/10 10:40
     */
    @Override
    public void sendHtmlMail(String to, String subject, String content) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        helper.setFrom(from);

        mailSender.send(message);

    }

    /**
     * @title 附件邮件
     * @description
     * @author admin
     * @param: to 邮件接收者
     * @param: subject 邮件主题
     * @param: content 邮件内容
     * @param: filePath 附件路径
     * @updateTime 2020/10/10 10:40
     */
    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        helper.setFrom(from);

        FileSystemResource file = new FileSystemResource(new File(filePath));
        String fileName = file.getFilename();
        helper.addAttachment(fileName, file);

        mailSender.send(message);
    }

    /**
     * @title 图片邮件
     * @description
     * @author admin
     * @param: to 邮件接收者
     * @param: subject 邮件主题
     * @param: content 邮件内容
     * @param: rscPath 图片路径
     * @param: rscId 图片ID
     * @updateTime 2020/10/10 10:41
     */
    @Override
    public void sendInlinkResourceMail(String to, String subject, String content, String rscPath, String rscId) {
        logger.info("发送静态邮件开始: {},{},{},{},{}", to, subject, content, rscPath, rscId);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;

        try {

            helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            helper.setFrom(from);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);
            mailSender.send(message);
            logger.info("发送静态邮件成功!");

        } catch (MessagingException e) {
            logger.info("发送静态邮件失败: ", e);
        }

    }



}
