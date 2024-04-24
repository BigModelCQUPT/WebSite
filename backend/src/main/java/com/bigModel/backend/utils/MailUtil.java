package com.bigModel.backend.utils;

import ch.qos.logback.core.util.FileUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.CellData;
import com.alibaba.excel.metadata.data.ClientAnchorData;
import com.alibaba.excel.metadata.data.ImageData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.util.FileUtils;
import com.alibaba.excel.util.MapUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.style.column.AbstractColumnWidthStyleStrategy;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bigModel.backend.config.filter.JwtAuthenticationTokenFilter;
import com.bigModel.backend.mapper.UserMapper;
import com.bigModel.backend.pojo.*;
import com.bigModel.backend.service.TokenService;
import com.bigModel.backend.service.TweetImageVideoService;
import com.bigModel.backend.service.TweetService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
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
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;


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

    private static final String SEND_ACCOUNT = "jiemaokeji@gmail.com"; //发送方地址
    // private static final String SEND_ACCOUNT = "lxb2000m@gmail.com"; //发送方地址
    // private static final String SEND_ACCOUNT = "lxb2000m@qq.com"; //发送方地址
    private static final String SMTP_HOST = "smtp.gmail.com";
    // private static final String SMTP_HOST = "smtp.qq.com";
    // 发件人名称
    private static final String staff_name = "发送方姓名"; //你的名字
    // 收件人姓名
    private static final String name = "接收方"; //你朋友的名字
    // 发送的主题
    private static final String subject = "通知"; //主题

    public static void sendMail(List<Tweet> tweetList) throws Exception {
        // QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // queryWrapper.eq("username", "admin");

        // User user = mailUtil.userMapper.selectOne(queryWrapper);

        // String toCountEmail = user.getEmail();
        // 接收方
        String toCountEmail = "lxb2000m@qq.com";
        // String toCountEmail = "8577618@gmail.com";

        // 参数配置，⽤于连接邮件服务器
        Properties props = new Properties();
        // 使⽤协议
        props.setProperty("mail.transport.protocol", "smtp");
        // 发件⼈邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.host", SMTP_HOST);
        // 需要请求认证
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.port", "465");

        // 创建会话对象，⽤于与邮箱服务器交互
        Session session = Session.getInstance(props);
        // 设置为debug模式，在控制台中可以查看详细的发送⽇志
        session.setDebug(true);
        // 创建⼀封邮件
        // 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();
        // 连接邮件服务器 unwymybasbxofidi
        transport.connect(SEND_ACCOUNT, mailUtil.tokenService.getToken("mailToken"));
        MimeMessage message = createMimeMessage(session, SEND_ACCOUNT, toCountEmail, name, subject, null, tweetList);
        // 发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        // 关闭连接
        transport.close();
    }

    public static MimeMessage createMimeMessage(Session session, String fromAccount, String toAccount,String name,String subject,String staff_name, List<Tweet> tweetList)
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
        // textPart.setContent("发现敏感推文，推文地址为" + "https://twitter.com/realcaixia/status/" + tweetId, "text/html;charset=UTF-8");
        textPart.setContent("发现敏感推文，具体信息看附件内容。", "text/html;charset=UTF-8");
        multipart.addBodyPart(textPart);

        BodyPart file = new MimeBodyPart();
        // DataSource dataSource = new ByteArrayDataSource(mailUtil.getExcel(tweetId), "application/excel");
        file.setFileName(MimeUtility.encodeText("推文信息.xlsx"));
        saveExcel(tweetList);
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
    public static void saveExcel(List<Tweet> tweetList) throws IOException {
        List<MailExcel> list = new ArrayList<>();
        AutoColumnWidthStyleStrategy longWidth = new AutoColumnWidthStyleStrategy();
        Map<String, Integer> zdyColumnWidth = new HashMap<>();
        //图片列名称，对应导出对象的列名称，图片列长度
        zdyColumnWidth.put("图片", 60);
        zdyColumnWidth.put("发布时间", 20);
        zdyColumnWidth.put("推文内容", 50);
        zdyColumnWidth.put("推文地址", 45);
        zdyColumnWidth.put("视频", 45);
        longWidth.setZdyColumnWidth(zdyColumnWidth);
        for (int pos = 0; pos < tweetList.size(); pos ++) {
            String tweetId = tweetList.get(pos).getTweetid();
            MailExcel mailExcel = new MailExcel();
            Tweet tweet = mailUtil.tweetService.getByTweetId(tweetId);
            mailExcel.setTweetid(tweetId);
            mailExcel.setUsername(tweet.getUsername());
            mailExcel.setText(tweet.getText());
            mailExcel.setUrl(tweet.getUrl());
            mailExcel.setPublishTime(tweet.getPublishTime());
            mailExcel.setFeedbackReason(tweet.getFeedbackReason());
            List<TweetImage> imageList = mailUtil.tweetImageVideoService.listImagesByTweetid(tweetId);
            List<TweetVideo> videoList = mailUtil.tweetImageVideoService.listVideosByTweetid(tweetId);
            // mailExcel.setImage(imageList.toString());
            mailExcel.setVideo(videoList.size() == 1 ? videoList.get(0).toString() : null); // 视频只存放链接
            // 图片列图片数
            AtomicReference<Integer> maxImageSize = new AtomicReference<>(imageList.size());
            List<ImageData> imageDataList = new ArrayList<>();
            int splitWidth = 2;
            int imageWidth = 80;
            int sumWidth = maxImageSize.get() * (imageWidth + splitWidth);
            for (int i = 1; i <= imageList.size(); i++) {
                String path = "/usr/java/img/" + tweetId + "_" + (i - 1) + ".jpg";
                int left = imageWidth * (i - 1) + i * splitWidth;
                int right = sumWidth - imageWidth - left;
                ImageData imageData = new ImageData();
                try {
                    imageData.setImage(FileUtils.readFileToByteArray(new File(path)));
                } catch (IOException e) {
                    System.out.println("无法将本地图片转为bytearray");
                    e.printStackTrace();
                }
                imageData.setImageType(ImageData.ImageType.PICTURE_TYPE_JPEG);
                imageData.setTop(1);
                imageData.setBottom(1);
                imageData.setLeft(left);
                imageData.setRight(right);
                imageData.setAnchorType(ClientAnchorData.AnchorType.DONT_MOVE_DO_RESIZE);
                imageDataList.add(imageData);
            }
            WriteCellData<Void> writeCellData = new WriteCellData<>();
            writeCellData.setImageDataList(imageDataList);
            mailExcel.setWriteCellData(writeCellData);
            list.add(mailExcel);
        }
        FileOutputStream fileOutputStream = new FileOutputStream(new File("/usr/java/excel/fujian.xlsx"));
        ExcelWriter excelWriter  = EasyExcel.write(fileOutputStream).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().head(MailExcel.class).registerWriteHandler(longWidth).build();
        excelWriter.write(list, writeSheet);
        excelWriter.finish();
        fileOutputStream.flush();
        fileOutputStream.close();
        excelWriter.close();
    }


    public void test() throws Exception {
        sendMail(new ArrayList<>());
        System.exit(0);
    }
}


class AutoColumnWidthStyleStrategy extends AbstractColumnWidthStyleStrategy {


    private static final int MAX_COLUMN_WIDTH = 255;

    //自定义列的列宽
    private Map<String, Integer> zdyColumnWidth = MapUtils.newHashMapWithExpectedSize(2);
    private final Map<Integer, Map<Integer, Integer>> CACHE = new HashMap<>();


    private final Map<Integer, Map<Integer, Integer>> cache = MapUtils.newHashMapWithExpectedSize(8);

    @Override
    protected void setColumnWidth(WriteSheetHolder writeSheetHolder, List<WriteCellData<?>> cellDataList, Cell cell,
                                  Head head,
                                  Integer relativeRowIndex, Boolean isHead) {
        boolean needSetWidth = isHead || !CollectionUtils.isEmpty(cellDataList);
        // if (!needSetWidth) {
        //     return;
        // }


        if (zdyColumnWidth.containsKey(cell.toString())) {
            writeSheetHolder.getSheet().setColumnWidth(cell.getColumnIndex(), zdyColumnWidth.get(cell.toString()) * 256);
            return;
        }

        Map<Integer, Integer> maxColumnWidthMap = cache.get(writeSheetHolder.getSheetNo());
        if (maxColumnWidthMap == null) {
            maxColumnWidthMap = new HashMap<Integer, Integer>(16);
            cache.put(writeSheetHolder.getSheetNo(), maxColumnWidthMap);
        }
        Integer columnWidth = dataLength(cellDataList, cell, isHead);
        if (columnWidth < 0) {
            return;
        }
        if (columnWidth > MAX_COLUMN_WIDTH) {
            columnWidth = MAX_COLUMN_WIDTH;
        }
        Integer maxColumnWidth = maxColumnWidthMap.get(cell.getColumnIndex());
        if (maxColumnWidth == null || columnWidth > maxColumnWidth) {
            maxColumnWidthMap.put(cell.getColumnIndex(), columnWidth);
            writeSheetHolder.getSheet().setColumnWidth(cell.getColumnIndex(), columnWidth * 256);
        }
    }

    private Integer dataLength(List<WriteCellData<?>> cellDataList, Cell cell, Boolean isHead) {
        if (isHead) {
            return cell.getStringCellValue().getBytes().length;
        }
        WriteCellData<?> cellData = cellDataList.get(0);
        CellDataTypeEnum type = cellData.getType();
        if (type == null) {
            return -1;
        }
        switch (type) {
            case STRING:
                return cellData.getStringValue().getBytes().length;
            case BOOLEAN:
                return cellData.getBooleanValue().toString().getBytes().length;
            case NUMBER:
                return cellData.getNumberValue().toString().getBytes().length;
            default:
                return -1;
        }
    }

    public void setZdyColumnWidth(Map<String, Integer> zdyColumnWidth) {
        this.zdyColumnWidth = zdyColumnWidth;
    }
}
