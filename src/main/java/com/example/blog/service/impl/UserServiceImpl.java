package com.example.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.entity.User;
import com.example.blog.payload.request.UpdateUserProfileReq;
import com.example.blog.payload.response.NotFoundResponse;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByUserName(String username) {
        // TODO Auto-generated method stub
        return userRepository.findByUsername(username).get();
    }

    @Override
    public User updateProfile(long id, UpdateUserProfileReq req) {
        // TODO Auto-generated method stub
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundResponse("User Not Found With Id:" + id));
        user.setEmail(req.getEmail());
        user.setName(req.getName());
        user.setCountry(req.getCountry());
        user.setAddress(req.getAddress());
        user.setPhone(req.getPhone());
        return userRepository.save(user);
    }
    
}
