package com.bigModel.backend.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class ImageDownloadUtil {

    /**
     * 文件下载到指定路径
     *
     * @param urlString 链接
     * @param savePath  保存路径
     * @param filename  文件名
     * @throws Exception
     */
    public static void download(String urlString, String savePath, String filename) throws IOException {
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        //设置请求超时
        connection.setConnectTimeout(10 * 1000);
        File sf = new File(savePath);
        if (!sf.exists()) {
            sf.mkdirs();
        }
        InputStream inputStream = connection.getInputStream();
        FileOutputStream outputStream = new FileOutputStream(sf.getPath() + "/" + filename);
        byte[] buffer = new byte[1024];
        int bytes;
        while ((bytes = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytes);
        }
        inputStream.close();
        outputStream.close();
        connection.disconnect();
    }
}