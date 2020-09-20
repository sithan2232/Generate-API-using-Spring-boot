package com.example.trainProject.Entity;

import javax.persistence.*;

@Entity
public class Like {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int like_id;
    private int user_id;
    private int post_id;
    public Like(){

    }

    public int getUser_id() {
        return user_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public int getLike_id() {
        return like_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public void setLike_id(int like_id) {
        this.like_id = like_id;
    }
}
