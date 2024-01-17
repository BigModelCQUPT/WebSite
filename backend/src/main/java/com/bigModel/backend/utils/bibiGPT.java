package com.bigModel.backend.utils;

import okhttp3.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class bibiGPT {

    public static String getSummary(String videoUrl){
        RestTemplate restTemplate = new RestTemplate();

        // Set the URL and headers
        String url = "https://bibigpt.co/api/open/CZ0LzCGBFuDT?url=" + videoUrl;

        // Make the HTTP GET request
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        // Access the response body and other information
        String responseBody = responseEntity.getBody();
        // Process the response as needed
        String summary = responseBody.substring(responseBody.indexOf("\"summary\":\"") + 11, responseBody.indexOf("\",\"costDuration\""));

        System.out.println(summary);
        return summary;
    }

}
