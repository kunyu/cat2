package com.crazyloong.cat.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author : crazyloongcat
 * @Date :2022/3/20 22:42
 * @Description :
 */
@Component
public class MailUtil {
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 普通邮件发送
     */
    public void sendSimpleMail(String messageStr) {
        // 构建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        // 设置邮件主题
        message.setSubject(messageStr);
        // 设置邮件发送者，这个跟application.yml中设置的要一致
        message.setFrom("crazyloongcat@163.com");
        // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似
        message.setTo("767630797@qq.com");
        // 设置邮件发送日期
        message.setSentDate(new Date());
        // 设置邮件的正文
        message.setText(messageStr);
        // 发送邮件
        javaMailSender.send(message);
    }

}
