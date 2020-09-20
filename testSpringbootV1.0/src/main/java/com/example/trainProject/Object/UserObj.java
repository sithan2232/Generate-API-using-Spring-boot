package com.example.trainProject.Object;

public class UserObj {
    private String current_username;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private int age;
    private String target_username;

    public String getTarget_username() {
        return target_username;
    }

    public void setTarget_username(String target_username) {
        this.target_username = target_username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCurrent_username() {
        return current_username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCurrent_username(String current_username) {
        this.current_username = current_username;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
