package com.example.trainProject.Controller;

import com.example.trainProject.Object.Authentication;
import com.example.trainProject.Entity.Login;
import com.example.trainProject.Entity.User;
import com.example.trainProject.Service.LoginRepository;
import com.example.trainProject.Service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.time.ZoneId;

//มีการเเก้ไช database user table ตัด login_id ออก ใน login table เพิ่ม user_id
@RestController
public class LoginController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    LoginRepository loginRepository;
    @PutMapping(path = "/log-in")
    public ResponseEntity<String> login(@RequestBody Authentication auth){
        String responseMessage="";
        HttpStatus status;
        User user;
        Login login;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (checkEmptyLoginInfo(auth)){
            responseMessage="Missing value";
            status=HttpStatus.OK;
        }
        else {
            user= userRepository.getUserByUsername(auth.getUsername());
            login=new Login();
            if(user==null){
                responseMessage="Incorrect username.";
                status=HttpStatus.UNAUTHORIZED;
            }
            else if (encoder.matches(auth.getPassword(),user.getPassword()) && user.getStatus()==false){
                user.setStatus(true);
                userRepository.save(user);
                login.setTime_login(LocalDateTime.now(ZoneId.of("Asia/Bangkok")));
                login.setUser_id(user.getUser_id());
                loginRepository.save(login);
                responseMessage="Successful login.";
                status=HttpStatus.OK;
            }
            else if (encoder.matches(auth.getPassword(),user.getPassword()) && user.getStatus()==true){
                responseMessage="User already active.";
                status=HttpStatus.OK;
            }
            else {
                responseMessage="Incorrect password.";
                status=HttpStatus.UNAUTHORIZED;
            }
        }
        return new ResponseEntity<String>(responseMessage,status);
    }
    @PutMapping(path = "/log-out")
    public ResponseEntity<String> logout(@RequestBody Authentication auth){
        String responseMessage="";
        HttpStatus status;
        User user = userRepository.getUserByUsername(auth.getUsername());
        Login login = loginRepository.getLoginByUserId(user.getUser_id());
        if(user==null){
            responseMessage="Incorrect username.";
            status=HttpStatus.UNAUTHORIZED;
        }
        else if (user.getStatus()==false){
            responseMessage="User not active now.";
            status=HttpStatus.OK;
        }
        else {
            user.setStatus(false);
            userRepository.save(user);
            login.setTime_logout(LocalDateTime.now(ZoneId.of("Asia/Bangkok")));
            loginRepository.save(login);
            responseMessage="Successful logout.";
            status=HttpStatus.OK;
        }
        return new ResponseEntity<String>(responseMessage,status);
    }
    private boolean checkEmptyLoginInfo(Authentication auth) {
        boolean result;
        try {
            if(auth.getPassword().equals("") || auth.getUsername().equals("")){
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

