package com.jas.web.utils;

public class SendMail {
      
    public static void main(String[] args) {  
        SendMail.send_163();  
    }  
      
    // 163邮箱  
    public static void send_163() {  
        // 这个类主要是设置邮件  
        MailSenderInfo mailInfo = new MailSenderInfo();  
        mailInfo.setMailServerHost("smtp.163.com");  
        mailInfo.setMailServerPort("25");  
        mailInfo.setValidate(true);  
        mailInfo.setUserName("13166956676@163.com"); // 实际发送者
        mailInfo.setPassword("juan0911081052");// 您的邮箱密码
        mailInfo.setFromAddress("13166956676@163.com"); // 设置发送人邮箱地址
        mailInfo.setToAddress("154938862@qq.com"); // 设置接受者邮箱地址
        mailInfo.setSubject("设置邮箱标题");  
        mailInfo.setContent("设置邮箱内容<b>h6</b>");  
        // 这个类主要来发送邮件  
        SimpleMailSender sms = new SimpleMailSender();  
        sms.sendTextMail(mailInfo); // 发送文体格式  
        sms.sendHtmlMail(mailInfo); // 发送html格式  
    }  
} 