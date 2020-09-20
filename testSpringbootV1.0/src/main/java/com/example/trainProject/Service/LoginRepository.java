package com.example.trainProject.Service;

import com.example.trainProject.Entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
//ระวังมี error กรณีไม่ได้ logout ปกติ
public interface LoginRepository extends JpaRepository<Login,Integer> {
    @Query(value = "select * from login where user_id=:user_id and time_logout IS NULL",nativeQuery = true)
    public Login getLoginByUserId(int user_id);

    @Transactional
    @Modifying
    @Query(value = "delete from `login` WHERE user_id=:user_id",nativeQuery = true)
    public void deleteLogin(int user_id);
}
