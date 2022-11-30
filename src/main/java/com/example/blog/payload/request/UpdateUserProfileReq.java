package com.example.blog.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserProfileReq {
    
    private String email;

    private String name;

    private String country;

    private String address;

    private String phone;
}
