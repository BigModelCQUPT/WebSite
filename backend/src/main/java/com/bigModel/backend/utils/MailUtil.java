package com.bigModel.backend.utils;

import org.springframework.stereotype.Component;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Component
public class MailUtil {

    private static final String SEND_ACCOUNT = "1072833411@qq.com"; //QQ邮箱地址
    private static final String SMTP_HOST = "smtp.qq.com";
    // 发件人名称
    private static final String staff_name = "发送方姓名"; //你的名字
    // 收件人姓名
    private static final String name = "接收方"; //你朋友的名字
    // 发送的主题
    private static final String subject = "邮件主题"; //主题

    public static void sendMail(String token) throws Exception {
        // 参数配置，⽤于连接邮件服务器
        Properties props = new Properties();
        // 使⽤协议
        props.setProperty("mail.transport.protocol", "smtp");
        // 发件⼈邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.host", SMTP_HOST);
        // 需要请求认证
        props.setProperty("mail.smtp.auth", "true");
        // 创建会话对象，⽤于与邮箱服务器交互
        Session session = Session.getInstance(props);
        // 设置为debug模式，在控制台中可以查看详细的发送⽇志
        session.setDebug(true);
        // 创建⼀封邮件
        MimeMessage message = createMimeMessage(session, SEND_ACCOUNT, "lxb2000m@gmail.com", name, subject,staff_name);

        // 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();
        // 连接邮件服务器
        transport.connect(SEND_ACCOUNT, token);
        // 发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        // 关闭连接
        transport.close();
    }

    public static MimeMessage createMimeMessage(Session session, String fromAccount, String toAccount,String name,String subject,String staff_name)
            throws Exception {
        // 1.创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 2.设置发件⼈，其中 InternetAddress 有三个参数，分别为：邮箱，显示的昵称，昵称的字符集编码
        message.setFrom(new InternetAddress(fromAccount, staff_name, "UTF-8"));

        // 3.设置收件⼈ - MimeMessage.RecipientType.TO
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toAccount ,name, "UTF-8"));

        // 7.设置邮件主题
        message.setSubject(subject,"UTF-8");

        // 8.设置邮件正⽂内容，指定格式为HTML格式
        message.setContent("context", "text/html;charset=UTF-8");
        // 9.设置显示发件时间
        message.setSentDate(new Date());

        // 10.保存设置
        message.saveChanges();

        return message;
    }
}
