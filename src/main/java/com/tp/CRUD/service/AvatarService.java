package com.tp.CRUD.service;

import com.tp.CRUD.entity.Avatar;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

public interface AvatarService {
    public Avatar uploadAvatar(MultipartFile avatar) throws IOException;
}
