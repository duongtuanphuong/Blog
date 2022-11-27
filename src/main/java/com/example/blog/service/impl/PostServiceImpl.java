package com.example.blog.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.entity.Image;
import com.example.blog.entity.Post;
import com.example.blog.entity.User;
import com.example.blog.payload.request.CreatePostRequest;
import com.example.blog.payload.response.NotFoundResponse;
import com.example.blog.repository.ImageRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Post> getListPost() {
        // TODO Auto-generated method stub
        return postRepository.findAll();
    }

    @Override
    public Post createPost(CreatePostRequest request) {
        // TODO Auto-generated method stub
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setDescription(request.getDescription());
        post.setContent(request.getContent());
        post.setCreateAt(new Timestamp(System.currentTimeMillis()));
        post.setPublishedAt(new Timestamp(System.currentTimeMillis()));
        post.setStatus(1);
        User user = userRepository.findByUsername(request.getUsername()).get();
        post.setCreateBy(user);
        ArrayList<Image> images = new ArrayList<>();
        for(long id : request.getImageIds()){
            Image image = imageRepository.findById(id).get();
            images.add(image);
        }
        post.setImages(images);
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(long id,CreatePostRequest request) {
        // TODO Auto-generated method stub
        Optional<Post> rs = postRepository.findById(id);
        if(rs.isEmpty()){
            throw new NotFoundResponse("Post not found with id : " + id);
        }

        Post post = rs.get();
        post.setTitle(request.getTitle());
        post.setDescription(request.getDescription());
        post.setContent(request.getContent());
        post.setModifiedAt(new Timestamp(System.currentTimeMillis()));
        post.setStatus(1);
        ArrayList<Image> images = new ArrayList<>();
        for(long imageId : request.getImageIds()){
            Image image = imageRepository.findById(imageId).get();
            images.add(image);
        }
        post.setImages(images);


        return postRepository.save(post);
    }

    @Override
    public Post findPostById(long id) {
        // TODO Auto-generated method stub
        return postRepository.findById(id).get();
    }

    @Override
    public void deletePostById(long id) {
        // TODO Auto-generated method stub
        Post post = postRepository.findById(id).orElseThrow(()-> new NotFoundResponse("Post Not Found With Id:" + id));
        post.getImages().remove(this);
        postRepository.delete(post);
    }
    
}
