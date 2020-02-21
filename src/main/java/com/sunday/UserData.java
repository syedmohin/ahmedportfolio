package com.sunday;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
public class UserData {
    @Id
    @GeneratedValue(strategy = AUTO)
    private int id;
    private String name;
    private String mail;
    private String msg;
    private String sub;

    public UserData(String name, String mail, String msg, String sub) {
        this.name = name;
        this.mail = mail;
        this.msg = msg;
        this.sub = sub;
    }
}
