package com.example.trainProject.Object;

public class UserBriefInfoForAdmin {
    private String first_name;
    private String last_name;
    private int age;
    private boolean status;
    private String username;

    public UserBriefInfoForAdmin(){

    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }
    public boolean getStatus(){
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

