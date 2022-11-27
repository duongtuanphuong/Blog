package com.example.blog.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.blog.entity.Image;
import com.example.blog.entity.User;
import com.example.blog.exception.BadRequestException;
import com.example.blog.exception.InternalServerException;
import com.example.blog.security.service.UserDetailsImpl;
import com.example.blog.service.ImageService;
import com.example.blog.service.UserService;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class ImageController {
    
    private static String UPLOAD_DIR  = System.getProperty("user.dir") + "/src/main/resources/static/photos/";

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/upload-file/{username}")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file,@PathVariable String username){
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);;
        if (originalFilename != null && originalFilename.length() > 0) {
            if (!extension.equals("png") && !extension.equals("jpg") && !extension.equals("gif") && !extension.equals("svg") && !extension.equals("jpeg")) {
                throw new BadRequestException("Không hỗ trợ định dạng file này");
            }
            try {
                Image img = new Image();
                img.setName(file.getName());
                img.setSize(file.getSize());
                img.setType(extension);
                img.setData(file.getBytes());
                User user = userService.findUserByUserName(username);
                String uid = UUID.randomUUID().toString();
                img.setUploadedAt(new Timestamp(System.currentTimeMillis()));
                img.setUploadedBy(user);
                String link = UPLOAD_DIR + uid + "." + extension;
                img.setLink(link);

                // Create file
                File serverFile = new File(link);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(file.getBytes());
                stream.close();

                imageService.save(img);
                return ResponseEntity.ok(img);
            } catch (Exception e) {
                throw new InternalServerException("Lỗi khi upload file");
            }
        }

        throw new BadRequestException("File không hợp lệ");

    }


    @GetMapping("/api/image/getImageByUser")
    public ResponseEntity<?> getListImageByUser(@RequestParam(name= "userId") long userId){
        List<Image> images = imageService.getListImageOfUser(userId);

        return ResponseEntity.ok(images);
    }
}
