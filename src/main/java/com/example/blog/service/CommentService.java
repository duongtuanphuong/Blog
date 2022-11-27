package com.example.blog.service;

import java.util.List;

import com.example.blog.entity.Comment;
import com.example.blog.entity.Post;
import com.example.blog.entity.User;
import com.example.blog.payload.request.CreateCommentRequest;

public interface CommentService {
    
    List<Comment> getAll();

    Comment getComment(long id, long postId);

    Comment saveComment(CreateCommentRequest request,long postId);

    Comment updateComment(long id, CreateCommentRequest request);

    void deleteCommentById(long id);
}
