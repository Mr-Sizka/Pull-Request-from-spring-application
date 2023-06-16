package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


@CrossOrigin
@Controller
@RequestMapping("user")
public class Demo {

    @GetMapping
    public String get() throws IOException {
        return pullReq();
    }

    public String pullReq() throws IOException {




        String owner = "Mr-Sizka";
        String repo = "demo";
        String USERNAME = "mr-sizka";
        String PASSWORD = "Sisuka#9ruuqpjpy";
        String baseBranch = "master";
        String headBranch = "feet4";
        String title = "Pull Request Title";
        String body = "Pull Request Description";

        String apiUrl = "https://api.github.com/repos/" + owner + "/" + repo + "/pulls";
        String auth = "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"; // Replace with your actual GitHub access token

        String credentials = USERNAME + ":" + PASSWORD;


        String jsonInputString = "{"
                + "\"title\": \"" + title + "\","
                + "\"body\": \"" + body + "\","
                + "\"head\": \"" + headBranch + "\","
                + "\"base\": \"" + baseBranch + "\""
                + "}";

        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + auth);
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int responseCode = conn.getResponseCode();
        String responseMessage = conn.getResponseMessage();

        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
        }

        System.out.println("Response Code: " + responseCode);
        System.out.println("Response Message: " + responseMessage);
        System.out.println("Response Body: " + response.toString());

        return "success";
    }



}
