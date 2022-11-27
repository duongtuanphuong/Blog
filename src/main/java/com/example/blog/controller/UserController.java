package com.example.blog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.entity.User;
import com.example.blog.service.UserService;


@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/currentUser")
    public ResponseEntity<?> currentUser(@RequestParam(name = "username")String username){
        
        User user = userService.findUserByUserName(username);

        return ResponseEntity.ok(user);
    }

}
