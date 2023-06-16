package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.io.IOException;


@CrossOrigin
@Controller
@RequestMapping("user")
public class Demo {

    private CreatePullRequest createPullRequest;

    public Demo(CreatePullRequest createPullRequest) {
        this.createPullRequest = createPullRequest;
    }

    @GetMapping
    public String get() throws IOException {
        return createPullRequest.pullReq();
    }
}
