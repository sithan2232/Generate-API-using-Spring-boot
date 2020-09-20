package com.example.trainProject.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Login {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int login_id;
    private LocalDateTime time_login;
    private LocalDateTime time_logout;
    private int user_id;
    public Login(){

    }

    public int getUser_id() {
        return user_id;
    }

    public int getLogin_id() {
        return login_id;
    }

    public LocalDateTime getTime_login() {
        return time_login;
    }

    public LocalDateTime getTime_logout() {
        return time_logout;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setLogin_id(int login_id) {
        this.login_id = login_id;
    }

    public void setTime_login(LocalDateTime time_login) {
        this.time_login = time_login;
    }

    public void setTime_logout(LocalDateTime time_logout) {
        this.time_logout = time_logout;
    }
}
