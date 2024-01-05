package com.bigModel.backend.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class chatGPT {

    public static HashMap<String, String> getAnswer(String content) {

        final String apiKey = "sk-LvLWgzMybUVMovfA0355Ec8599Fb42A7A9D163D4041fD94c";
        final String baseUrl = "https://aihubmix.com/v1";
        final String model = "gpt-3.5-turbo";

        System.out.println("进入chatGPT分析");
        HashMap<String, String> answerHash = new HashMap<>();
        answerHash.put("content", content);
//        try {
//            // Replace "sk-xxx" with your actual API key
//            String apiKey = "sk-LvLWgzMybUVMovfA0355Ec8599Fb42A7A9D163D4041fD94c";
//
//            // Replace "https://orisound.cn//v1" with the actual base URL
//            String baseUrl = "https://aihubmix.com";
//
//            URL url = new URL(baseUrl + "/chat/completions");
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("POST");
//            connection.setRequestProperty("Content-Type", "application/json");
//            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
//            connection.setDoOutput(true);
//
//            // Replace "Say this is a test" with your actual message
//            String userMessage = "请记住，你是一位语句分析师，能够对句子按照类别库进行分类。类别库包括:枪支、毒品、诈骗、洗钱、赌博、网安威胁、反共反体制、民运、组党结社、维权抗议、民族分裂、宗教极端、非法宗教、恐怖暴力、海外利益等。句子如下 " + content + " 如果你发现句子包含上述类别或者可能即将进行或者正在进行的游行示威活动，如果包含，只需返回是，如果不包含，只需返回否。";
//
//            String payload = "{\n  \"messages\": [\n    {\n      \"role\": \"user\",\n      \"content\": \"" + userMessage + "\"\n    }\n  ],\n  \"model\": \"gpt-3.5-turbo\"\n}";
//
//
//            try (OutputStream os = connection.getOutputStream()) {
//                byte[] input = payload.getBytes("utf-8");
//                os.write(input, 0, input.length);
//            }
//
//            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
//                StringBuilder response = new StringBuilder();
//                String responseLine;
//                while ((responseLine = br.readLine()) != null) {
//                    response.append(responseLine.trim());
//                }
//                System.out.println("原始" + response);
//                String res = response.substring(response.indexOf("\"content\""));
//                String[] tmp = res.split("\"");
//                System.out.println(tmp[3]);
//                answerHash.put("answer", tmp[3]);
//            }
//            connection.disconnect();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        RestTemplate restTemplate = new RestTemplate();
        String userMessage = "请记住，你是一位语句分析师，能够对句子按照类别库进行分类。类别库包括:枪支、毒品、诈骗、洗钱、赌博、网安威胁、反共反体制、民运、组党结社、维权抗议、民族分裂、宗教极端、非法宗教、恐怖暴力、海外利益等。句子如下 " + content + " 如果你发现句子包含上述类别或者可能即将进行或者正在进行的游行示威活动，只需返回是，反之只需返回否。无需返回任何其他答案。";
        // Create a JSON request payload
        String jsonPayload = "{ \"messages\": [ { \"role\": \"user\", \"content\": \"" + userMessage + "\" } ], \"model\": \"" + model + "\" }";

        String endpoint = baseUrl + "/chat/completions";

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + apiKey);

        // Create the request
        HttpEntity<String> entity = new HttpEntity<>(jsonPayload, headers);

        // Make the request
        ResponseEntity<String> response = restTemplate.exchange(endpoint, HttpMethod.POST, entity, String.class);
        String strResponse = response.toString();
        String res = strResponse.substring(strResponse.indexOf("\"content\""));
        String[] tmp = res.split("\"");
        answerHash.put("answer", tmp[3]);

        return answerHash;
    }
}
