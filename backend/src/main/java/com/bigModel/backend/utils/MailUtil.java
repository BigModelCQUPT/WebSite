package com.bigModel.backend.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bigModel.backend.config.filter.JwtAuthenticationTokenFilter;
import com.bigModel.backend.mapper.UserMapper;
import com.bigModel.backend.pojo.*;
import com.bigModel.backend.service.TokenService;
import com.bigModel.backend.service.TweetImageVideoService;
import com.bigModel.backend.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.activation.*;
import javax.annotation.PostConstruct;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;


@Component
public class MailUtil {

    private static MailUtil mailUtil;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private TweetService tweetService;
    @Autowired
    private TweetImageVideoService tweetImageVideoService;

    @PostConstruct
    public void init() {
        mailUtil = this;
        mailUtil.userMapper = this.userMapper;
        mailUtil.tokenService = this.tokenService;
        mailUtil.tweetService = this.tweetService;
        mailUtil.tweetImageVideoService = this.tweetImageVideoService;
    }

    private static final String SEND_ACCOUNT = "lxb2000m@qq.com"; //QQ邮箱地址
    private static final String SMTP_HOST = "smtp.qq.com";
    // 发件人名称
    private static final String staff_name = "发送方姓名"; //你的名字
    // 收件人姓名
    private static final String name = "接收方"; //你朋友的名字
    // 发送的主题
    private static final String subject = "通知"; //主题

    public static void sendMail(List<Tweet> list) throws Exception {
        // QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // queryWrapper.eq("username", "admin");

        // User user = mailUtil.userMapper.selectOne(queryWrapper);

        // String toCountEmail = user.getEmail();
        String toCountEmail = "lxb2000m@gmail.com";

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
        // 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();
        // 连接邮件服务器 unwymybasbxofidi
        transport.connect(SEND_ACCOUNT, mailUtil.tokenService.getToken("mailToken"));
        for (int i = 0; i < 1; i ++) {

            MimeMessage message = createMimeMessage(session, SEND_ACCOUNT, toCountEmail, name, subject, null, "1707416653095735754");
            // 发送邮件
            transport.sendMessage(message, message.getAllRecipients());
        }
        // 关闭连接
        transport.close();
    }

    public static MimeMessage createMimeMessage(Session session, String fromAccount, String toAccount,String name,String subject,String staff_name, String tweetId)
            throws Exception {
        // 1.创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 2.设置发件⼈，其中 InternetAddress 有三个参数，分别为：邮箱，显示的昵称，昵称的字符集编码
        message.setFrom(new InternetAddress(fromAccount, staff_name, "UTF-8"));
        // 3.设置收件⼈ - MimeMessage.RecipientType.TO
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toAccount ,name, "UTF-8"));

        Multipart multipart = new MimeMultipart();

        // 邮件内容
        BodyPart textPart = new MimeBodyPart();
        textPart.setContent("发现敏感推文，推文地址为" + "https://twitter.com/realcaixia/status/" + tweetId, "text/html;charset=UTF-8");
        multipart.addBodyPart(textPart);

        BodyPart file = new MimeBodyPart();
        // DataSource dataSource = new ByteArrayDataSource(mailUtil.getExcel(tweetId), "application/excel");
        file.setFileName(MimeUtility.encodeText("推文信息.xlsx"));
        saveExcel(tweetId);
        DataSource dataSource = new FileDataSource("./excel/fujian.xlsx");
        // file.setDataHandler(new DataHandler(mailUtil.getExcel(tweetId)));
        file.setDataHandler(new DataHandler(dataSource));
        multipart.addBodyPart(file);

        message.setContent(multipart);

        // 7.设置邮件主题
        message.setSubject(subject,"UTF-8");
        // 9.设置显示发件时间
        message.setSentDate(new Date());
        // 10.保存设置
        message.saveChanges();
        return message;
    }
    public static void saveExcel(String tweetId) throws IOException {
        MailExcel mailExcel = new MailExcel();
        Tweet tweet = mailUtil.tweetService.getByTweetId(tweetId);
        mailExcel.setTweetid(tweetId);
        mailExcel.setUsername(tweet.getUsername());
        mailExcel.setText(tweet.getText());
        mailExcel.setUrl(tweet.getUrl());
        mailExcel.setPublishTime(tweet.getPublishTime().toString());
        List<TweetImage> imageList = mailUtil.tweetImageVideoService.listImagesByTweetid(tweetId);
        List<TweetVideo> videoList = mailUtil.tweetImageVideoService.listVideosByTweetid(tweetId);
        mailExcel.setImage(imageList.toString());
        mailExcel.setVideo(videoList.toString());
        List<MailExcel> list = new ArrayList<>();
        list.add(mailExcel);
        FileOutputStream fileOutputStream = new FileOutputStream(new File("./excel/fujian.xlsx"));
        ExcelWriter excelWriter  = EasyExcel.write(fileOutputStream).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().head(MailExcel.class).build();
        excelWriter.write(list, writeSheet);
        excelWriter.finish();
        fileOutputStream.flush();
        fileOutputStream.close();
        excelWriter.close();
    }
    // @Scheduled(fixedRate = 1)
    public void test() throws Exception {
        sendMail(null);
        System.exit(0);
    }
}
