package com.example.demo;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class CreatePullRequest {

    public String pullReq() throws IOException {

        String owner = "mr-sizka";
        String repo = "demo";
        String baseBranch = "master";
        String headBranch = "feet3";
        String title = "Pull Request Title";
        String body = "Pull Request Description";

        String apiUrl = "https://api.github.com/repos/" + owner + "/" + repo + "/pulls";
        String auth = "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"; // Replace with your actual GitHub access token

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
