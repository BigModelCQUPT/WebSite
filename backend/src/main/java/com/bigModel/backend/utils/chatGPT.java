package com.bigModel.backend.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class chatGPT {

    public static HashMap<String, Object> getAnswer(String content) {
        HashMap<String, Object> answerHash = new HashMap<>();
        answerHash.put("content", content);
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
            String userMessage = content + " 请分析上述文字是否为政治敏感，你只需回答是或不是";

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

                String res = response.substring(response.indexOf("\"content\""));
                String[] tmp = res.split("\"");
                System.out.println(tmp[3]);
                answerHash.put("answer", tmp[3]);
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answerHash;
    }
}
