package com.luobo.service;

import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

public interface MailService {
    public void sendSimpleMail(String to,String subject,String content);
    public void sendHtmlMail(String to, String subject, String content) throws MessagingException;
    public void sendAttachmentsMail(String to, String subject, String content,String filePath) throws MessagingException;
    public void sendInlinkResourceMail(String to, String subject, String content,String rscPath, String rscId);
}
