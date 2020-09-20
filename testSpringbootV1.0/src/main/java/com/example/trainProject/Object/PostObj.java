package com.example.trainProject.Object;

public class PostObj {
    private String username;
    private String topic;
    private String description;
    private int color;
    private int post_id;

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getUsername() {
        return username;
    }

    public int getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }

    public String getTopic() {
        return topic;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
