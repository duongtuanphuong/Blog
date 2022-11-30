package com.example.blog.service;

import com.example.blog.entity.User;
import com.example.blog.payload.request.UpdateUserProfileReq;

public interface UserService {

    User findUserByUserName(String username);

    User updateProfile(long id,UpdateUserProfileReq req);

}
