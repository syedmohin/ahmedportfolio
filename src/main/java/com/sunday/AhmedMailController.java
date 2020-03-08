package com.sunday;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AhmedMailController {

    private final AhmedMailService service;
    private final Repo repo;

    @RequestMapping("/getdata")
    public String confirm(@RequestParam("name") String userName, @RequestParam("mail") String mail, @RequestParam("msg") String msg, @RequestParam("sub") String sub) {
        UserData data = new UserData(userName, mail, msg, sub);
        service.sendNotification(data);
        log.info("Data recevied from User {}", userName);
        repo.save(data);
        log.info("Data recevied from User {}", userName);
        return "details";
    }

    @ResponseBody
    @GetMapping("/name")
    public String name() {
        log.info("Calling Goku Army ");
        return "<body style='background-color:black;'><h1  style='color:tomato;'>Syed Mohiuddin";
    }
    @ResponseBody
    @GetMapping("/haku")
    public String friend() {
        log.info("Calling Goku Army ");
        return "<body style='background-color:black;'><h1 style='color:green;'>Mohd Abdul Hakeem";
    }
    
    @ResponseBody
    @GetMapping("/goku")
    public String goku() {
        log.info("Calling Goku Army ");
        return "<body style='background-color:black;'><h1 style='color:red;'>Goku Amry";
    }

    @ResponseBody
    @GetMapping("data")
    public List<UserData> getData() {
        return service.getData();
    }

    @RequestMapping("path")
    public String path() {
        return Paths.get("").toAbsolutePath() + "resume.pdf";
    }
}
