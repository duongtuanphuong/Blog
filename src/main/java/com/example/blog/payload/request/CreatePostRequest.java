package com.example.blog.payload.request;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.example.blog.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostRequest {
    
    private String title;

    private String description;

    private String content;

    private int status;

    private String username;

    @JsonProperty("image_ids")
    private ArrayList<Long> imageIds;

}
