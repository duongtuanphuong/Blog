package com.example.blog.controller;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.entity.Comment;
import com.example.blog.entity.Post;
import com.example.blog.entity.User;
import com.example.blog.payload.request.CreateCommentRequest;
import com.example.blog.security.service.UserDetailsImpl;
import com.example.blog.service.CommentService;
import com.example.blog.service.PostService;
import com.example.blog.service.UserService;

@RestController
@RequestMapping("/api/post/{postId}/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;


    @PostMapping("/create")
    public ResponseEntity<?> createComment(@Valid @RequestBody CreateCommentRequest request,@PathVariable long postId){
        Comment comment = commentService.saveComment(request, postId);

        return ResponseEntity.ok(comment);
    }


}
