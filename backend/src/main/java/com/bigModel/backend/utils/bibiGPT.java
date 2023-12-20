package com.bigModel.backend.utils;

import okhttp3.*;

import java.io.IOException;

public class bibiGPT {

    public void getContent(){
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");

        Request request = new Request.Builder()
                .url("https://bibigpt.co/api/open/LLZaSC9Vhgaq?url=https://www.youtube.com/watch?v=LNWLSQl_jfc")
                .method("GET", body)
                .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                .addHeader("Accept", "*/*")
                .addHeader("Host", "bibigpt.co")
                .addHeader("Connection", "keep-alive")
                .build();

        try {
            Response response = client.newCall(request).execute();

            // Do something with the response here if needed

            // Make sure to close the response to release resources
            if (response != null) {
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
