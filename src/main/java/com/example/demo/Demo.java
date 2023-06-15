package com.example.demo;

import org.eclipse.egit.github.core.PullRequestMarker;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.service.PullRequestService;
import org.eclipse.egit.github.core.service.RepositoryService;

import java.io.IOException;


@CrossOrigin
@Controller
@RequestMapping("user")
public class Demo {

    @GetMapping
    public String get(){
        return pullReq();
    }

    public String pullReq() {

        String username = "mr-sizka";
        String password = "Sisuka#9ruuqpjpy";
        String repositoryOwner = "Mr-Sizka";
        String repositoryName = "demo";

        GitHubClient client = new GitHubClient();
        client.setCredentials(username, password);


        try {
            // Create a new pull request
            PullRequestService pullRequestService = new PullRequestService(client);
            PullRequest pullRequest = new PullRequest();
            pullRequest.setTitle("My Pull Request");
            pullRequest.setBody("This is a pull request from the Java application.");

            System.out.println(1);
            // Specify the source and destination branches
            pullRequest.setBase(new PullRequestMarker().setRef("main").setLabel("main"));
            pullRequest.setHead(new PullRequestMarker().setRef("feet3").setLabel("feet3"));


            // Get the repository information
            RepositoryId repositoryId = RepositoryId.create(repositoryOwner, repositoryName);
            System.out.println(1);
            RepositoryService repositoryService = new RepositoryService(client);
            System.out.println(1);
            Repository repository = repositoryService.getRepository(repositoryId);
            System.out.println(1);

            System.out.println(repository.getDescription());
            System.out.println(repository.getGitUrl());
            // Create the pull request
            pullRequestService.createPullRequest(repository, pullRequest);

            System.out.println("Pull request created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "success";
    }
}
