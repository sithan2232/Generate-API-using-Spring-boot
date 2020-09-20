package com.example.trainProject.Controller;

import com.example.trainProject.Entity.*;
import com.example.trainProject.Object.PostObj;
import com.example.trainProject.Object.UserBriefInfo;
import com.example.trainProject.Service.LikeRepository;
import com.example.trainProject.Service.PostRepository;
import com.example.trainProject.Service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LikeRepository likeRepository;
    @GetMapping("/post/getAllPostInfo")
    public List<Post> retrievePostInformation(){
        List<Post> post=postRepository.findAll();
        UserBriefInfo userWriterBriefInfo;
        UserBriefInfo userLikesBriefInfo;
        User userWriter;
        User userWhoLike;
        List<Like> likeList;
        List<UserBriefInfo> userLikeList;
        for(int i=0;i<post.size();i++){
            userLikeList=new ArrayList<>();
            userWriter=userRepository.getOne(post.get(i).getUser_id());
            likeList=likeRepository.findByPostId(post.get(i).getPost_id());
            userWriterBriefInfo=new UserBriefInfo(
                    userWriter.getFirst_name(),
                    userWriter.getLast_name(),
                    userWriter.getAge()
            );
            post.get(i).setPostWriter(userWriterBriefInfo);
            for (int j=0;j<likeList.size();j++){
                userWhoLike=userRepository.getOne(likeList.get(j).getUser_id());
                userLikesBriefInfo=new UserBriefInfo(
                        userWhoLike.getFirst_name(),
                        userWhoLike.getLast_name(),
                        userWhoLike.getAge()
                );
                userLikeList.add(userLikesBriefInfo);
            }
            post.get(i).setLikes(userLikeList);
        }
        return post;
    }

    @PostMapping(path = "/post/writePost")
    public ResponseEntity<String> writePost(@RequestBody PostObj postObj){
        String response="";
        Post post = new Post();
        User user = userRepository.getUserByUsername(postObj.getUsername());
        if(checkEmptyPostInformation(postObj)){
            response="Missing information";
        }
        else {
            if(user==null){
                response="Incorrect username";
            }
            else if(user.getStatus()==false){
                response="Please login first";
            }
            else {
                if(postObj.getColor()>3
                        || postObj.getColor()<1){
                    post.setColor(1);
                }
                else {
                    post.setColor(postObj.getColor());
                }
                post.setTopic_name(postObj.getTopic());
                post.setDescription(postObj.getDescription());
                post.setUser_id(user.getUser_id());
                postRepository.save(post);
                response="Write post success.";
            }
        }
        return new ResponseEntity<String>(response,HttpStatus.OK);
    }

    @PutMapping(path = "/post/editPost")
    public ResponseEntity<String> editPost(@RequestBody PostObj postObj){
        String response="";
        Post post;
        User user;
        if(checkEmptyPostInformation(postObj) ||
                checkEmptyUserInformation(postObj)){
            response="Missing value";
        }
        else {
            post= postRepository.getPostById(postObj.getPost_id());
            user= userRepository.getUserByUsername(postObj.getUsername());
            if(user==null){
                response="Incorrect username";
            }
            else if(post==null){
                response="Cannot detect post(wrong post id)";
            }
            else if(user.getStatus()==false){
                response="Please login first";
            }
            else if(user.getUser_id()!=post.getUser_id()){
                response="Username dose  not match.";
            }
            else{
                if(postObj.getColor()>3 ||
                        postObj.getColor()<1){
                    post.setColor(1);
                }
                else {
                    post.setColor(postObj.getColor());
                }
                post.setTopic_name(postObj.getTopic());
                post.setDescription(postObj.getDescription());
                postRepository.save(post);
                response="Edit post success.";
            }
        }

        return new ResponseEntity<String>(response,HttpStatus.OK);
    }
    @DeleteMapping(path = "/post/deletePost")
    public ResponseEntity<String> deletePost(@RequestBody PostObj postObj){
        String response="";
        Post post = postRepository.getPostById(postObj.getPost_id());
        User user = userRepository.getUserByUsername(postObj.getUsername());
        if(checkEmptyUserInformation(postObj)){
            response="Missing value";
        }
        else {
            if(user==null){
                response="Incorrect username";
            }
            else if(post==null){
                response="Cannot detect post(wrong post id)";
            }
            else if(user.getStatus()==false){
                response="Please login first";
            }
            else if(user.getUser_id()!=post.getUser_id()){
                response="Username dose not match.";
            }
            else {
                likeRepository.deleteLikeByPostId(post.getPost_id());
                postRepository.delete(post);
                response="Delete success.";
            }
        }

        return new ResponseEntity<String>(response,HttpStatus.OK);
    }

    @PostMapping(path = "/post/likePost")
    public ResponseEntity<String> likePost(@RequestBody PostObj postObj){
        String response;
        User user = userRepository.getUserByUsername(postObj.getUsername());
        Post post = postRepository.getPostById(postObj.getPost_id());
        Like like;
        if (checkEmptyUserInformation(postObj)){
            response="Missing value";
        }
        else {
            if(user==null){
                response="Incorrect username";
            }
            else if(post==null){
                response="Cannot detect post(wrong post id)";
            }
            else if(user.getStatus()==false){
                response="Please login first";
            }
            else {
                like=likeRepository.queryLikeByPostIdAndUserId(user.getUser_id(),post.getPost_id());
                if(like!=null){
                    response="You already liked the post.";
                }
                else {
                    likeRepository.createLike(user.getUser_id(),post.getPost_id());
                    response="Like success.";
                }
            }
        }
        return new ResponseEntity<String>(response,HttpStatus.OK);
    }
    private boolean checkEmptyPostInformation(PostObj postObj) {
        boolean result;
        try {
            if (postObj.getTopic().equals("") ||
                    postObj.getDescription().equals("") ||
                    postObj.getUsername().equals("")) {
                result = true;
            } else {
                result = false;
            }
        }
        catch (NullPointerException error){
            result=true;
        }
        return result;
    }
    private boolean checkEmptyUserInformation(PostObj postObj) {
        boolean result;
        try {

            if (postObj.getUsername().equals("") ) {
                result = true;
            } else {
                result = false;
            }
        }
        catch (NullPointerException error){
            result=true;
        }
        return result;
    }




//    วิธีเช็ค BCryptPasswordEncoder
//    @GetMapping(value = "/test")
//    public String test(@RequestParam String input){
//        User user= userRepository.getOne(1);
//        String x1=new BCryptPasswordEncoder().encode("test");
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        if(encoder.matches(input, x1)){
//            return "yes";
//        }
//
//        return new BCryptPasswordEncoder().encode("password");
//    }

}
