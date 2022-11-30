package com.example.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.blog.entity.Comment;
import com.example.blog.entity.Post;
import com.example.blog.entity.User;
import com.example.blog.payload.request.CreateCommentRequest;
import com.example.blog.payload.response.BadRequestResponse;
import com.example.blog.payload.response.NotFoundResponse;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Comment> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Comment getComment(long id, long postId) {
        // TODO Auto-generated method stub
        Post post = postRepository.findById(postId).orElseThrow(()-> new NotFoundResponse("Post Not Found With Id:" + postId));
        Comment comment = commentRepository.findById(id).orElseThrow(()-> new NotFoundResponse("Comment Not Found With Id:" + id));
        if (comment.getPost().getId() == (post.getId())) {
			return comment;
		}

        throw new BadRequestResponse(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        
    }

    @Override
    public Comment saveComment(CreateCommentRequest request,long postId) {
        // TODO Auto-generated method stub
        Post post = postRepository.findById(postId).orElseThrow(() -> new NotFoundResponse("Post Not Found With Id:" + postId));

        Comment comment = new Comment();
        comment.setContent(request.getContent());
        User user = userRepository.findByUsername(request.getUsername()).get();
        comment.setPost(post);
        comment.setUser(user);

        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(long id, CreateCommentRequest request) {
        // TODO Auto-generated method stub
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new NotFoundResponse("Comment Not Found With Id:" + id));
        comment.setContent(request.getContent());
        return commentRepository.save(comment);
    }

    @Override
    public void deleteCommentById(long id) {
        // TODO Auto-generated method stub
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new NotFoundResponse("Comment Not Found With Id: " +id));
        commentRepository.delete(comment);
    }


    
}
