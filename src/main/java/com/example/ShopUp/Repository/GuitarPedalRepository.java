package com.example.ShopUp.Repository;


import com.example.ShopUp.model.GuitarPedal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuitarPedalRepository extends JpaRepository<GuitarPedal , Long> {
    GuitarPedal findGuitarPedalById(long id);
    List<GuitarPedal> findGuitarPedalsByIsShowAndQuantity(boolean show , int quantity);
    List<GuitarPedal> findGuitarPedalsByIsShowAndQuantityIsAfter(boolean show , int quantity);
}
