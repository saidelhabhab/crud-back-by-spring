package com.tp.CRUD.service.serviceImpl;

import com.tp.CRUD.entity.Avatar;
import com.tp.CRUD.repository.AvatarRepo;
import com.tp.CRUD.service.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

@Service
public class AvatarServiceImpl implements AvatarService {
    @Autowired
    private AvatarRepo avatarRepo;
    @Override
    public Avatar uploadAvatar(MultipartFile avatar) throws IOException {
        Avatar photo = new Avatar();
        photo.setAvatar(avatar.getBytes());
        return avatarRepo.save(photo);
    }
}
