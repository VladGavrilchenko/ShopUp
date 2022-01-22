package com.example.ShopUp.Repository;


import com.example.ShopUp.model.Mediator;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediatorRepository extends JpaRepository<Mediator , Long> {
    Mediator findMediatorById(long id);
    List<Mediator> findMediatorsByIsShowAndQuantity(boolean show , int quantity);
    List<Mediator> findMediatorsByIsShowAndQuantityIsAfter(boolean show , int quantity);
}
