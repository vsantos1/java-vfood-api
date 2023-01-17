package com.ifood.repository;

import com.ifood.model.Image;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileRepository {

    Image save(String name);

    void upload(Long productId, String filename, MultipartFile file);

    Resource download(String filename) throws IOException;
}


