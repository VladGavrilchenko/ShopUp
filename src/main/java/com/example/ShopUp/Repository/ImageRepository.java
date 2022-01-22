package com.example.ShopUp.Repository;


import com.example.ShopUp.model.ImageOfProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageOfProduct, Long> {
}
