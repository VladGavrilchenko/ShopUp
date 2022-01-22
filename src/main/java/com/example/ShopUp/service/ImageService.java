package com.example.ShopUp.service;

import com.example.ShopUp.Repository.ImageRepository;
import com.example.ShopUp.model.ImageOfProduct;
import org.springframework.stereotype.Service;



@Service
public class ImageService {

    private ImageRepository imageRepository;

    public  ImageService(ImageRepository imageRepository)
    {
        this.imageRepository = imageRepository;
    }


    public void addImage(ImageOfProduct image)
    {
        imageRepository.save(image);
    }
}
