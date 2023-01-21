package com.ifood.resource;

import com.ifood.model.Product;
import com.ifood.service.FileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.minidev.json.JSONObject;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/files")
@Tag(name = "File", description = "File endpoint API")
public class FileResource {


    private final FileService fileService;

    public FileResource(FileService fileService) {
        this.fileService = fileService;
    }


    @PostMapping(value = "/products/{product_id}/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<JSONObject> uploadPhoto(@PathVariable("product_id") Long productId, @RequestParam("file") MultipartFile file, HttpServletRequest request) {


        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID() + "_" + file.getName();
        fileService.upload(productId, fileName, file);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("download_url", request.getContextPath() + "/api/v1/files/" + fileName);
        jsonObject.put("status", HttpStatus.CREATED.value());
        jsonObject.put("message", "File uploaded successfully");

        return ResponseEntity.status(HttpStatus.CREATED).body(jsonObject);
    }

}
