package com.example.ShopUp.Repository;


import com.example.ShopUp.model.GuitarStrap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuitarStrapRepository extends JpaRepository<GuitarStrap , Long> {
    GuitarStrap findGuitarStrapById(long id);
    List<GuitarStrap> findGuitarStrapsByIsShowAndQuantity(boolean show , int quantity);
    List<GuitarStrap> findGuitarStrapsByIsShowAndQuantityIsAfter(boolean show , int quantity);
}
