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
        return "Syed Mohiuddin";
    }
    @ResponseBody
    @GetMapping("/haku")
    public String friend() {
        log.info("Calling Goku Army ");
        return "Mohd Abdul Hakeem";
    }
    
    @ResponseBody
    @GetMapping("/goku")
    public String goku() {
        log.info("Calling Goku Army ");
        return "Goku Amry";
    }

    @ResponseBody
    @RequestMapping("/getresume")
    public ResponseEntity<?> downloadFile(HttpServletResponse response) {
//        Resource resource = new ClassPathResource("resume.pdf");
        String path = String.valueOf(Paths.get("").toAbsolutePath());
//        URL path = AhmedMailController.class.getResource("resume.pdf");

        File f = new File("D:\\Programs\\Spring\\Ahmed\\src\\main\\resources\\resume.pdf");
//        File f = new File("https://drive.google.com/open?id=1Fh24_ojO2LySGWAdm1EnT05kAo-99jHq");
        log.info(f.getAbsolutePath());
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=resume.pdf");
        response.setHeader("Content-Transfer-Encoding", "binary");
        BufferedOutputStream bufferedOutputStream = null;
        try {
            bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            FileInputStream fileInputStream = new FileInputStream(f);
            int len;
            byte[] buf = new byte[1024];
            while ((len = fileInputStream.read(buf)) > 0) {
                bufferedOutputStream.write(buf, 0, len);
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("application/pdf"))
                    .body(bufferedOutputStream);
        } catch (Exception e) {
            log.error("Unable to download file  {} ", e.toString());
        } finally {
            try {
                if (bufferedOutputStream != null) bufferedOutputStream.close();
                response.flushBuffer();
            } catch (Exception e) {
                log.error("Unable to download file  {} ", e.getMessage());
            }
        }
        return new ResponseEntity<>("error ouccred", HttpStatus.INTERNAL_SERVER_ERROR);
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
