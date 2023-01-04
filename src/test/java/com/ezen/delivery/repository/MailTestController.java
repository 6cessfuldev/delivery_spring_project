package com.ezen.delivery.repository;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MailTestController {
 
    @Autowired
    JavaMailSenderImpl mailSender;
    
    // MimeMessage 객체를 직접 생성하여 메일을 발송하는 방법
    @Test
    public void mailSendTest() throws Exception{
        
        
    	String subject = "test 메일 하하하";
        String content = "메일 테스트 내용" + "<img src=\"https://www.google.com/imgres?imgurl=https%3A%2F%2Fimg1.daumcdn.net%2Fthumb%2FR1280x0.fjpg%2F%3Ffname%3Dhttp%3A%2F%2Ft1.daumcdn.net%2Fbrunch%2Fservice%2Fuser%2F32E9%2Fimage%2FBA2Qyx3O2oTyEOsXe2ZtE8cRqGk.JPG&imgrefurl=https%3A%2F%2Fbrunch.co.kr%2F%40happying%2F66&tbnid=uBQ8cebvR-okYM&vet=12ahUKEwi1hJjYoav8AhWOOpQKHdX0BeoQMygNegUIARD3AQ..i&docid=0RhOIZ63_Xb9-M&w=960&h=640&q=%EA%B0%95%EC%95%84%EC%A7%80&ved=2ahUKEwi1hJjYoav8AhWOOpQKHdX0BeoQMygNegUIARD3AQ\">";
        String from = "myeonk@naver.com";
        String to = "myeonk@naver.com";
        
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");
            
            mailHelper.setFrom(from);
            mailHelper.setTo(to);
            mailHelper.setSubject(subject);
            mailHelper.setText(content, true);
            
            FileSystemResource file = new FileSystemResource(new File("C:\\Users\\ezen\\Desktop\\logo.png")); 
            mailHelper.addAttachment("logo.png", file);         
            
            mailSender.send(mail);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
}
