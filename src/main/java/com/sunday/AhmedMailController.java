package com.sunday;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AhmedMailController {

    private final AhmedMailService service;

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

    @ResponseBody
    @GetMapping("/name")
    public String name() {
        log.info("Calling GOku owner ");
        return "Syed Mohiuddin < Goku Army";
    }

    @ResponseBody
    @RequestMapping("/getresume")
    public ResponseEntity<?> downloadFile() throws Exception {
        return service.downloadFile();
    }

    @ResponseBody
    @GetMapping("data")
    public List<UserData> getData(){
        return service.getData();
    }
}
