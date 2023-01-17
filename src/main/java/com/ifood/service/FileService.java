package com.ifood.service;

import com.ifood.model.Image;
import com.ifood.model.Product;
import com.ifood.repository.FileRepository;
import com.ifood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class FileService implements FileRepository {

    private Path hasFile;
    @Value("${file.upload-dir}")
    private String UPLOAD_DIR = "";

    private final ProductService productService;
    private final ProductRepository productRepository;

    public FileService(ProductService productService,
                       ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }


    @Override
    public Image save(String name) {
        return null;
    }

    @Override
    public void upload(Long productId, String filename, MultipartFile file) {
        if (file.isEmpty()) {
            throw new ResourceNotFoundException("Cannot store empty file");
        }

        Product product = this.productService.findById(productId);
        Image image = new Image();

        image.setUrl(filename);
        product.setImage(image);
        String filePath = UPLOAD_DIR + filename;

        Path path = Path.of(filePath);

        try {
            file.transferTo(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        productRepository.save(product);

    }

    public Optional<Product> findByfileName(String fileName) {
        return productRepository.findProductByImage_Url(fileName);
    }

    @Override
    public Resource download(String filename) throws IOException {
        Path path = Paths.get(UPLOAD_DIR + filename);


        Files.list(path).forEach(
                file -> {
                    if (file.getFileName().toString().startsWith(filename)) {
                        hasFile = file;
                        return;

                    }
                }
        );
        if (hasFile != null) {
            return new UrlResource(hasFile.toUri());
        }

        return null;


    }
}
