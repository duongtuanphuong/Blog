package com.example.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.entity.Post;
import com.example.blog.entity.User;
import com.example.blog.payload.request.CreatePostRequest;
import com.example.blog.security.service.UserDetailsImpl;
import com.example.blog.service.ImageService;
import com.example.blog.service.PostService;
import com.example.blog.service.UserService;

@RestController
@RequestMapping("/api/post")
public class PostController {
    
    @Autowired
    private PostService postService;

    @Autowired
    private ImageService imageService;

    @Autowired UserService userService;


    @GetMapping("/")
    public ResponseEntity<?> getListPost(){
        List<Post> listPost = postService.getListPost();
        return ResponseEntity.ok(listPost);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable long id){
        Post post = postService.findPostById(id);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getListPostByUser(@PathVariable long id){
        List<Post> listPost = postService.getListPostByUser(id);
        return ResponseEntity.ok(listPost);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@Valid @RequestBody CreatePostRequest request){
        Post post = postService.createPost(request);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePost(@PathVariable long id, @Valid @RequestBody CreatePostRequest request){
        Post post = postService.updatePost(id, request);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePost(@PathVariable long id){
        postService.deletePostById(id);

        return ResponseEntity.ok(HttpStatus.OK);
    }

}
