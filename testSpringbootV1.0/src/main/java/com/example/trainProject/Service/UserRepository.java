package com.example.trainProject.Service;

import com.example.trainProject.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//ติดปันหา sql เทียบ username เเล้วมันเเยก upper /lower case ไม่ได้เช่นมันจับ admin=ADMIN;
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "select * from user where username=binary :username",nativeQuery = true)
    User getUserByUsername(String username);
}
