package com.sunday;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Paths;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AhmedMailController {

    @Value("class:resume.docx")
    public static Resource resource;
    private final AhmedMailService service;

//    @RequestMapping(path = {"index.html", "", "/"})
//    public String homePage() {
//        return "index";
//    }

    @RequestMapping("/getdata")
    public String confirm(HttpServletRequest request) {
        String userName = request.getParameter("name");
        String mail = request.getParameter("mail");
        String msg = request.getParameter("msg");
        String sub = request.getParameter("sub");
        UserData data = new UserData(userName, mail, msg, sub);
        log.info("Data recevied from User {}", userName);
        service.sendNotification(data);
        return "details";
    }
//    @RequestMapping("/error")
//    public String error(){
//        return "error/404";
//    }

    @ResponseBody
    @GetMapping("/name")
    public String name() {
        log.info("Calling GOku owner ");
        return "Syed Mohiuddin < Goku Army";
    }

    @GetMapping("/getresume")
    public ResponseEntity<?> downloadFile() {
        try {
            return service.downloadFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("path")
    @ResponseBody
    public String path() {

        return null;
    }
}
