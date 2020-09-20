package com.example.trainProject.Service;

import com.example.trainProject.Entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.;

import javax.transaction.Transactional;
import java.util.List;

public interface LikeRepository extends JpaRepository<Like,Integer> {

    @Query(value = "SELECT * FROM `like` where post_id=:post_id",nativeQuery = true)
    public List<Like> findByPostId(int post_id);


    @Transactional
    @Modifying
    @Query(value = "delete from `like` WHERE post_id=:post_id",nativeQuery = true)
    public void deleteLikeByPostId(int post_id);


    @Query(value = "select * from `like` where post_id=:post_id and user_id=:user_id",nativeQuery = true)
    public Like queryLikeByPostIdAndUserId(int user_id, int post_id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `like`( `user_id`, `post_id`) VALUES (:user_id,:post_id)",nativeQuery = true)
    public void createLike(int user_id,int post_id);

    @Transactional
    @Modifying
    @Query(value = "delete from `like` WHERE user_id=:user_id",nativeQuery = true)
    public void deleteLikeByUserId(int user_id);

}
