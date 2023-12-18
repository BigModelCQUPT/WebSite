package com.bigModel.backend.task;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class test {

    public static void main(String[] args) {
        try {
            // Replace "sk-xxx" with your actual API key
            String apiKey = "sk-LvLWgzMybUVMovfA0355Ec8599Fb42A7A9D163D4041fD94c";

            // Replace "https://orisound.cn//v1" with the actual base URL
            String baseUrl = "https://orisound.cn/v1";

            URL url = new URL(baseUrl + "/chat/completions");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setDoOutput(true);

            // Replace "Say this is a test" with your actual message
            String userMessage = "在信息爆炸的时代，很多信息我们无法全面接收，我们需要从中筛选出一些我们感 兴趣的或者说对我们有用的信息进行接收。 怎么选择呢，关键词提取就是其中一个很好 的方法。 如果我们可以准确地将所有文档都用几个简单的关键词描述出来 ，那我们单看 几个关键词就可以了解一篇文章是不是我们所需要的 ，这样会大大提高我们的信息获取效率。" + " 提取上述文本的三个关键词";

            String payload = "{\n  \"messages\": [\n    {\n      \"role\": \"user\",\n      \"content\": \"" + userMessage + "\"\n    }\n  ],\n  \"model\": \"gpt-3.5-turbo\"\n}";


            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = payload.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
