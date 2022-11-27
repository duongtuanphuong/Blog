package com.example.blog.service;

import java.util.List;

import com.example.blog.entity.Image;

public interface ImageService {

    List<Image> getList();

    Image getImageById(long id);

    public void save(Image image);

    public List<Image> getListImageOfUser(long userId);

    public void deleteImage(String uploadDir,String fileName);
}
