package com.example.trainProject.Service;

import com.example.trainProject.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post,Integer> {
    @Query(value = "select * from post where post_id=:post_id",nativeQuery = true)
    public Post getPostById(int post_id);
}
