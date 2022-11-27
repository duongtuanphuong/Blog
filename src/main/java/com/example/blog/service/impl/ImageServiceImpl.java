package com.example.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.entity.Image;
import com.example.blog.payload.response.NotFoundResponse;
import com.example.blog.repository.ImageRepository;
import com.example.blog.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public void save(Image image) {
        // TODO Auto-generated method stub
        imageRepository.save(image);
    }

    @Override
    public List<Image> getListImageOfUser(long userId) {
        // TODO Auto-generated method stub
        List<Image> images = imageRepository.getListImageOfUser(userId);
        return images;
    }

    @Override
    public void deleteImage(String uploadDir, String fileName) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Image> getList() {
        // TODO Auto-generated method stub
        return imageRepository.findAll();
    }

    @Override
    public Image getImageById(long id) {
        // TODO Auto-generated method stub
        Image image = imageRepository.findById(id).orElseThrow(()-> new NotFoundResponse("Image Not Found With Id:" + id));
        return image;
    }
    
}
