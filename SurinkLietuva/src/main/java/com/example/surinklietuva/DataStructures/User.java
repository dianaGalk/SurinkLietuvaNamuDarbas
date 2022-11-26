package com.example.surinklietuva.DataStructures;

import java.util.List;

public class User {
    private String name;
    private String surname;
    private String username;
    private String mail;
    private String password;
    private List<Magnet> magnetList;

    public User() {
    }

    public User(String name, String surname, String username, String mail, String password, List<Magnet> magnetList) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.magnetList = magnetList;
    }

    public String getUserInfoForDataBase() {
        String userInfo = "";
        userInfo += this.getName() + "||";
        userInfo += this.getSurname() + "||";
        userInfo += this.getUsername() + "||";
        userInfo += this.getMail() + "||";
        userInfo += this.getPassword() + "||";
        if (!getMagnetList().isEmpty()) {
            for (int i = 0; i < this.getMagnetList().size(); i++) {
                userInfo += this.getMagnetList().get(i).getName() + "||";
            }
        }
        return userInfo;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Magnet> getMagnetList() {
        return magnetList;
    }

    public void setMagnetList(List<Magnet> magnetList) {
        this.magnetList = magnetList;
    }
}

