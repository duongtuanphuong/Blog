package com.example.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.blog.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM post WHERE create_by = ?1 Order by id DESC")
    List<Post> getListPostByUser(long id);
}
