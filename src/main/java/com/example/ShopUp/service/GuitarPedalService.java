package com.example.ShopUp.service;

import com.example.ShopUp.Repository.GuitarPedalRepository;
import com.example.ShopUp.model.GuitarPedal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuitarPedalService {

    private GuitarPedalRepository guitarPedalRepository;


    GuitarPedalService(GuitarPedalRepository guitarPedalRepository) {
        this.guitarPedalRepository = guitarPedalRepository;
    }

    public void saveGuitarPedal(GuitarPedal guitarPedal){
        guitarPedalRepository.save(guitarPedal);
    }

    public List<GuitarPedal> findAll(){
        return  guitarPedalRepository.findAll();
    }

    public  GuitarPedal findById( long id)
    {
        return guitarPedalRepository.findGuitarPedalById(id);
    }

    public List<GuitarPedal> findAllShowAndInQuality(){
        return  guitarPedalRepository.findGuitarPedalsByIsShowAndQuantityIsAfter(true , 0);}

    public List<GuitarPedal> findAllShowAndInNoQuality(){
        return  guitarPedalRepository.findGuitarPedalsByIsShowAndQuantity(true , 0);}



}
