package com.sunday;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class AhmedMailService {

    private final JavaMailSender javaMailSender;
    private final Repo repo;

    public void sendNotification(UserData userData) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("deccanbesporty@gmail.com");
        mail.setTo("ahmed.mohammad1121@gmail.com");
        mail.setText(userData.getMsg() + "   ->   " + userData.getMail() + "   ->   " + userData.getName());
        mail.setSubject(userData.getSub());
        mail.setReplyTo("deccanbesporty@gmail.com");
        javaMailSender.send(mail);
        log.info("mail send successfull by {}", userData.getName());
        repo.save(userData);
        log.info("Data store in Database by {} ", userData.getName());
    }

    public ResponseEntity<Object> downloadFile() throws Exception {

        log.info("resume path is set here");

        File file = new ClassPathResource("resume.docx").getFile();
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=\"" + file.getName()+"\"");
        headers.add("Cache-Control", "no-cache, no-store, must-revalidte");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
                .body(resource);
    }

}
