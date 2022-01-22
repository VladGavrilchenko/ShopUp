package com.example.ShopUp.Repository;

import com.example.ShopUp.model.GuitarString;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GuitarStringRepository extends JpaRepository<GuitarString,Long>{

    GuitarString findGuitarStringsById(long id);
    List<GuitarString> findGuitarStringsByIsShowAndQuantity(boolean show , int quantity);
    List<GuitarString> findGuitarStringsByIsShowAndQuantityIsAfter(boolean show , int quantity);
}
