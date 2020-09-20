package com.example.trainProject.Controller;

import com.example.trainProject.Entity.User;
import com.example.trainProject.Object.UserBriefInfoForAdmin;
import com.example.trainProject.Object.UserObj;
import com.example.trainProject.Service.LikeRepository;
import com.example.trainProject.Service.LoginRepository;
import com.example.trainProject.Service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    LoginRepository loginRepository;
    @Autowired
    LikeRepository likeRepository;
    @PostMapping(path = "/user/addUser")
    public ResponseEntity<String> addUser(@RequestBody UserObj userObj){
        String response="";
        User current_username=userRepository.getUserByUsername(userObj.getCurrent_username());
        User new_username=userRepository.getUserByUsername(userObj.getUsername());
        if(checkEmptyAddUserInfo(userObj)){
            response="Missing value";
        }
        else {
            if(current_username==null){
                response="Current username incorrect";
            }
            else if(current_username.getStatus()==false){
                response="Please login first";
            }
            else if(current_username.getType()!=1){
                response="You are not admin.";
            }
            else if(new_username!=null){
                response="Username already used.";
            }
            else {
                new_username=new User();
                new_username.setFirst_name(userObj.getFirstname());
                new_username.setLast_name(userObj.getLastname());
                new_username.setAge(userObj.getAge());
                new_username.setPassword(new BCryptPasswordEncoder().encode(userObj.getPassword()));
                new_username.setUsername(userObj.getUsername());
                new_username.setType(0);
                new_username.setStatus(false);
                userRepository.save(new_username);
                response="Add user success.";
            }
        }
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    @DeleteMapping(value = "/user/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestBody UserObj userObj){
        String response="";
        User current_username=userRepository.getUserByUsername(userObj.getCurrent_username());
        User target_username=userRepository.getUserByUsername(userObj.getTarget_username());

        if(current_username==null){
            response="Current username incorrect";
        }
        else if(current_username.getStatus()==false){
            response="Please login first";
        }
        else if(current_username.getType()!=1){
            response="You are not admin.";
        }
        else if(target_username==null){
            response="Target username incorrect.";
        }
        else if(target_username.getType()==1){
            response="Cannot delete admin account.";
        }
        else {
            likeRepository.deleteLikeByUserId(target_username.getUser_id());
            loginRepository.deleteLogin(target_username.getUser_id());
            userRepository.delete(target_username);
            response="Delete success.";
        }
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/user/getUserInfo/")
    public UserBriefInfoForAdmin getUserProfile(@RequestParam String current_username,
                                                @RequestParam String target_username){
        UserBriefInfoForAdmin userBriefInfoForAdmin = new UserBriefInfoForAdmin();
        User current_user=userRepository.getUserByUsername(current_username);
        User target_user=userRepository.getUserByUsername(target_username);
        if(current_user==null){
            userBriefInfoForAdmin=null;
        }
        else if(current_user.getStatus()==false){
            userBriefInfoForAdmin=null;
        }
        else if(current_user.getType()!=1){
            userBriefInfoForAdmin=null;
        }
        else if(target_user==null){
            userBriefInfoForAdmin=null;
        }
        else {
            userBriefInfoForAdmin.setFirst_name(target_user.getFirst_name());
            userBriefInfoForAdmin.setLast_name(target_user.getLast_name());
            userBriefInfoForAdmin.setAge(target_user.getAge());
            userBriefInfoForAdmin.setStatus(target_user.getStatus());
            userBriefInfoForAdmin.setUsername(target_user.getUsername());

        }
        return userBriefInfoForAdmin;
    }

    @PutMapping(path = "/user/updateUser")
    public ResponseEntity<String> updateUserProfile(@RequestBody UserObj userObj){
        String response="";
        User current_username=userRepository.getUserByUsername(userObj.getCurrent_username());
        User target_username=userRepository.getUserByUsername(userObj.getTarget_username());
        User checkUsernameInDatabase=userRepository.getUserByUsername(userObj.getUsername());
        if(current_username==null){
            response="Current username incorrect";
        }
        else if(current_username.getStatus()==false){
            response="Please login first";
        }
        else if(current_username.getType()!=1){
            response="You are not admin.";
        }
        else if(target_username==null){
            response="Target username incorrect.";
        }
        else if(userObj.getUsername()==null){
            response="Value missing.";
        }
        else if(checkUsernameInDatabase!=null){
            response="Username already used.";
        }
        else {
            target_username.setFirst_name(userObj.getFirstname());
            target_username.setLast_name(userObj.getLastname());
            target_username.setAge(userObj.getAge());
            target_username.setUsername(userObj.getUsername());
            target_username.setPassword(new BCryptPasswordEncoder().encode(userObj.getPassword()));
            userRepository.save(target_username);
            response="Update success.";
        }

        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/user/getMyselfInfo/")
    public User getMyselfProfile(@RequestParam String username){
        User user=userRepository.getUserByUsername(username);
        if(user==null){
            user=null;
        }
        else if(user.getStatus()==false){
            user=null;
        }
        else {
            user.setPassword("-");
        }
        return user;
    }

    @PutMapping(path = "/user/updateMyself")
    public ResponseEntity<String> updateMyselfProfile(@RequestBody UserObj userObj){
        String response="";
        User user=userRepository.getUserByUsername(userObj.getCurrent_username());
        User new_username=userRepository.getUserByUsername(userObj.getUsername());
        if(user==null){
            response="Incorrect username.";
        }
        else if(user.getStatus()==false){
            response="Please login first.";
        }
        else if(userObj.getUsername()==null){
            response="Value missing.";
        }
        else if(new_username!=null){
            response="Username already used.";
        }
        else {
            user.setFirst_name(userObj.getFirstname());
            user.setLast_name(userObj.getLastname());
            user.setAge(userObj.getAge());
            user.setUsername(userObj.getUsername());
            user.setPassword(new BCryptPasswordEncoder().encode(userObj.getPassword()));
            userRepository.save(user);
            response="Update success.";
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    private boolean checkEmptyAddUserInfo(UserObj userObj) {
        boolean result;
        try {
            if(userObj.getCurrent_username().equals("") ||
                    userObj.getFirstname().equals("") ||
                    userObj.getLastname().equals("") ||
                    userObj.getUsername().equals("") ||
                    userObj.getPassword().equals("")){
                result=true;
            }
            else {
                result=false;
            }
        }
        catch (NullPointerException error){
            result=true;
        }
        return result;
    }
}
