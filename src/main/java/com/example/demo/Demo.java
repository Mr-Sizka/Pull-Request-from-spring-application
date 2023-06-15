package com.example.demo;

import org.eclipse.egit.github.core.*;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.IssueService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.eclipse.egit.github.core.service.PullRequestService;
import org.eclipse.egit.github.core.service.RepositoryService;

import java.io.IOException;


@CrossOrigin
@Controller
@RequestMapping("user")
public class Demo {

    @GetMapping
    public PullRequest get() throws IOException {
        return pullReq();
    }

    public PullRequest pullReq() throws IOException {

        String username = "mr-sizka";
        String password = "Sisuka#9ruuqpjpy";
        String repositoryOwner = "Mr-Sizka";
        String repositoryName = "demo";

        /*GitHubClient client = new GitHubClient();
        client.setCredentials(username, password);


        try {
            // Create a new pull request
            PullRequestService pullRequestService = new PullRequestService(client);
            PullRequest pullRequest = new PullRequest();
            pullRequest.setTitle("My Pull Request");
            pullRequest.setBody("This is a pull request from the Java application.");

            // Specify the source and destination branches
            pullRequest.setBase(new PullRequestMarker().setRef("feet3").setLabel("feet3"));
            pullRequest.setHead(new PullRequestMarker().setRef("main").setLabel("main"));


            // Get the repository information
            RepositoryId repositoryId = RepositoryId.create(repositoryOwner, repositoryName);
            RepositoryService repositoryService = new RepositoryService(client);
            Repository repository = repositoryService.getRepository("Mr-Sizka","demo");

            // Create the pull request
            pullRequestService.createPullRequest(repository, pullRequest);

            System.out.println("Pull request created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "success";*/

        /*RepositoryService repositoryService = new RepositoryService();
        repositoryService.getClient().setCredentials(username, password);
        org.eclipse.egit.github.core.Repository openmrs = null;
        try {
            openmrs = repositoryService.getRepository(repositoryOwner, repositoryName);
        } catch (IOException e) {
            throw new RuntimeException("Failed to get repository data", e);
        }
        PullRequestService pullRequestService = new PullRequestService();
        pullRequestService.getClient().setCredentials(username, password);
        PullRequest pr = new org.eclipse.egit.github.core.PullRequest();
        pr.setTitle("pull request method");
        pr.setHead(new PullRequestMarker().setLabel("feet3"));
        pr.setBase(new PullRequestMarker().setLabel("main"));
        pr.setBody("request.getDescription()");

        try {
            return pullRequestService.createPullRequest(openmrs, 3,"feet3","main");
        } catch (IOException e) {
            System.out.println("failed");
            throw new RuntimeException("Failed to create pull request:"+pr, e);
        }*/


        GitHubClient client = new GitHubClient();
        client.setCredentials(username, password);

        PullRequestService pullRequestService = new PullRequestService(client);
        pullRequestService.getClient().setCredentials(username, password);
        RepositoryId repo = RepositoryId.createFromId(repositoryOwner + "/" + repositoryName);

        PullRequest request = new PullRequest();
        request.setTitle("Please merge");
        request.setBody("Please merge");
        request.setHead(new PullRequestMarker().setRef("feet3").setLabel("feet3"));
        request.setBase(new PullRequestMarker().setRef("master").setLabel("master"));

        return pullRequestService.createPullRequest(repo,request);
    }

}
