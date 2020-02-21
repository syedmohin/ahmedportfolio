package com.sunday;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

//    public ResponseEntity<Object> downloadFile() throws Exception {
//
//        log.info("resume path is set here");
//
//        Resource file = new ClassPathResource("static/resume.pdf");
//        InputStream stream = file.getInputStream();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "attachment; filename=resume.pdf");
//        headers.add("Cache-Control", "no-cache, no-store, must-revalidte");
//        headers.add("Content-Transfer-Encoding", "binary");
//        try {
//            BufferedOutputStream inputStream = new BufferedOutputStream(re);
//            FileInputStream fis = new FileInputStream(file.getFile());
//            int len;
//            byte[] buf = new byte[1024];
//            while ((len = fis.read(buf)) > 0) {
//                inputStream.write
//            }
//        }
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentType(MediaType.parseMediaType("application/pdf"))
//                .body(stream);
//    }

    public List<UserData> getData() {
        Iterable<UserData> data = repo.findAll();
        List<UserData> list = new ArrayList<>();
        data.forEach(list::add);
        return list;
    }

}
