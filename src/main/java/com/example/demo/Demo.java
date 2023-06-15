package com.example.demo;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@Controller
@RequestMapping("/user")
public class Demo {

    @GetMapping
    public String get(){
        return pullReq(
                "C:\\Users\\sisuk\\Downloads\\demo",
                "https://github.com/Mr-Sizka/demo.git",
                "Code-Piggy",
                "Sisuka#9ruuqpjpy"
        );
    }

    public String pullReq(String dir, String gitDir, String userName, String pass) {

        try {

            Repository localRepo = new FileRepository(dir + "/.git");
            Git git = new Git(localRepo);
            CredentialsProvider cp = new UsernamePasswordCredentialsProvider(userName, pass);
            git.pull().setCredentialsProvider(cp).call();
            git.close();
            localRepo.close();

        } catch (Exception e) {
            return e.getLocalizedMessage();
        }
        return "success";

    }
}
