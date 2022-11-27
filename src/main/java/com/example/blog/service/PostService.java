package com.example.blog.service;

import java.util.List;

import com.example.blog.entity.Post;
import com.example.blog.entity.User;
import com.example.blog.payload.request.CreatePostRequest;

public interface PostService {
    
    List<Post> getListPost();

    Post createPost(CreatePostRequest request);

    Post updatePost(long id,CreatePostRequest request);

    Post findPostById(long id);
    
    void deletePostById(long id);

}
