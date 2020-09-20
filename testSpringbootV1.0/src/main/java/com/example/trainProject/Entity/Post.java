package com.example.trainProject.Entity;

import com.example.trainProject.Object.UserBriefInfo;

import javax.persistence.*;
import java.util.List;

@Entity
public class Post {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int post_id;
    private String Topic_name;
    private String description;
    private int color;
    private int user_id;
    @Transient
    private UserBriefInfo postWriter;
    @Transient
    private List<UserBriefInfo> likeLists;
    public Post(){

    }

    public List<UserBriefInfo> getLikes() {
        return likeLists;
    }

    public void setLikes(List<UserBriefInfo> likes) {
        this.likeLists = likes;
    }

    public void setPostWriter(UserBriefInfo postWriter) {
        this.postWriter = postWriter;
    }

    public UserBriefInfo getPostWriter() {
        return postWriter;
    }



    public int getPost_id() {
        return post_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTopic_name() {
        return Topic_name;
    }

    public String getDescription() {
        return description;
    }

    public int getColor() {
        return color;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public void setTopic_name(String topic_name) {
        Topic_name = topic_name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getUser_id() {
        return user_id;
    }
}
