package com.tp.CRUD.controller;

import com.tp.CRUD.entity.Avatar;
import com.tp.CRUD.entity.User;
import com.tp.CRUD.exception.ResourceNotFoundException;
import com.tp.CRUD.service.AvatarService;
import com.tp.CRUD.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3001/")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AvatarService avatarService;


    @PostMapping("/upload")
    public ResponseEntity<Avatar> uploadAvatar(@RequestParam("avatar") MultipartFile avatar) throws IOException {

        try {
            Avatar uploadedAvatar = avatarService.uploadAvatar(avatar);
            return ResponseEntity.status(HttpStatus.CREATED).body(uploadedAvatar);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @RequestMapping(path = "/add", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public   ResponseEntity<User> save(@RequestPart(value = "user",required = true) User newUser,
                                       @RequestPart(value = "photo",required = true)  MultipartFile photo,
                                       @RequestPart(value = "video",required = true)  MultipartFile video)
            throws IOException {


        if (!photo.isEmpty()) {
            try {
                byte[] bytes = photo.getBytes();
                byte[] bytes2 = video.getInputStream().readAllBytes();
                // Creating the directory to store file
                String rootPath = System.getProperty("d:/");
                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists())
                    dir.mkdirs();

                User user = new User();
                user.setUsername(newUser.getUsername());
                user.setEmail(newUser.getEmail());
                //user.setPhoto(newUser.getPhoto());
                user.setPhoto(bytes);
                user.setVideo(bytes2);

                //  newUser.setPhoto(photo.getBytes());
                userService.save(user);
                return ResponseEntity.ok().build();
                //return new  ResponseEntity<>(userService.save(newUser), HttpStatus.CREATED);

            }catch (Exception e) {
                    return null;
            }
        } else {
            throw  new ResourceNotFoundException("not found file");
        }

    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return  ResponseEntity.ok(userService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody User user,@PathVariable("id")  Long id) {
        User user1 = userService.update(user, id);
        return  ResponseEntity.accepted().body(user1);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("downloadImage/{id}")
    public ResponseEntity<? extends Object> getIdImage(@PathVariable("id")  Long id) {
        
        User user = userService.getIdImage(id);

        if (user != null && user.getPhoto() != null ) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "user_image.png");

            return new ResponseEntity<byte[]>(user.getPhoto(), headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
        //return userService.getIdImage(id);
    }

    @GetMapping("displayVideo/{id}")
    public ResponseEntity<? extends Object> getVideoBuId(@PathVariable("id")  Long id) throws FileNotFoundException {

        User user = userService.getVideoById(id);

        if (user != null && user.getVideo() != null ) {


            return new ResponseEntity<>(user.getVideo() , HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
        //return userService.getIdImage(id);
    }
}
