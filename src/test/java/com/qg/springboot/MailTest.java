package com.qg.springboot;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:高键城
 * @time：
 * @Discription：
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    /**
     * 发送附件测试
     */
    @Test
    public void sendFileSimpleMail(){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage , true);
            //设置发送人和收件人
            helper.setFrom(sender);
            helper.setTo(sender);
            //设置主题
            helper.setSubject("Hello ~");
            helper.setText("带附件的邮件");
            //发送附件设置附件
            FileSystemResource file = new FileSystemResource(new File("src\\main\\resources\\static\\image\\baby.jpg"));
            //加入邮件
            helper.addAttachment("file",file);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(mimeMessage);
    }


    /**
     * 发送带有静态文件的邮件
     * @throws MessagingException
     */
    @Test
    public void sendInlineMail() throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(sender);
        helper.setTo(sender);
        //设置主题
        helper.setSubject("带静态文件的邮件");
        //第二个参数指定发送的是HTML格式，同时cid：是固定的写法
        helper.setText("<html><body>邮件内容 <img src='cid:picture' ></body></html>",true);

        FileSystemResource resource = new FileSystemResource(new File("src\\main\\resources\\static\\image\\baby.jpg"));
        helper.addInline("pictureId",resource);

        mailSender.send(message);
    }

    /**
     * 发送模版邮件
     * @throws MessagingException
     * @throws IOException
     * @throws TemplateException
     */
    @Test
    public void sendTemplateMail() throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage , true);
        helper.setFrom(sender);
        helper.setTo(sender);
        helper.setSubject("模版邮件");

        Map<String, String> model = new HashMap<>();
        model.put("husband","wilderGao");
        model.put("wife","purpleHuang");

        Template template = freeMarkerConfigurer.getConfiguration().getTemplate("index.html");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template , model);
        helper.setText(html, true);

        mailSender.send(mimeMessage);
    }

}
