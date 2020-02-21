package com.sunday;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.nio.file.Paths;
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
        log.info("Calling Goku Army ");
        return "Syed Mohiuddin < Goku Army";
    }

    @ResponseBody
    @RequestMapping("/getresume")
    public void downloadFile(HttpServletResponse response) {
//        Resource resource = new ClassPathResource("resume.pdf");
        String path = String.valueOf(Paths.get("").toAbsolutePath());
        log.info(path+"\\resume.pdf");
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=resume.pdf");
        response.setHeader("Content-Transfer-Encoding", "binary");
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            FileInputStream fileInputStream = new FileInputStream(path+"\\resume.pdf");
            int len;
            byte[] buf = new byte[1024];
            while ((len = fileInputStream.read(buf)) > 0) {
                bufferedOutputStream.write(buf, 0, len);
            }
            bufferedOutputStream.close();
            response.flushBuffer();
        } catch (Exception e) {
            log.error("Unable to download file  {} ",e.getMessage());
        }
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
