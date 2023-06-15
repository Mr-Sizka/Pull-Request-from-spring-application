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
@RequestMapping("user")
public class Demo {

    @GetMapping
    public String get(){
        return pullReq(
                "C:\\Users\\sisuk\\Downloads\\demo",
                "https://github.com/Mr-Sizka/demo.git",
                "mr-sizka",
                "Sisuka#9ruuqpjpy"
        );
    }

    public String pullReq(String dir, String gitDir, String userName, String pass) {

        try {

            Repository localRepo = new FileRepository(dir + "/.git");
            System.out.println(1);
            Git git = new Git(localRepo);
            System.out.println(2);
            CredentialsProvider cp = new UsernamePasswordCredentialsProvider(userName, pass);
            System.out.println(3);
            git.pull().setCredentialsProvider(cp).call();
            System.out.println(4);
            git.close();
            System.out.println(5);
            localRepo.close();
            System.out.println(6);

        } catch (Exception e) {
            return e.getLocalizedMessage();
        }
        System.out.println(7);
        return "success";

    }
}
