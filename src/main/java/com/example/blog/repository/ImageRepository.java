package com.example.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.blog.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
    
    @Query(nativeQuery = true, value = "SELECT * FROM image WHERE uploaded_by = ?1")
    public List<Image> getListImageOfUser(long userId);

    public Image findByLink(String link);

}
