package com.example.ShopUp.service;

import com.example.ShopUp.Repository.GuitarRepository;
import com.example.ShopUp.model.Guitar;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuitarService {

    private GuitarRepository guitarRepository;

    GuitarService(GuitarRepository guitarRepository){
        this.guitarRepository =guitarRepository;
    }



    public void saveGuitar(Guitar guitar){
        guitarRepository.save(guitar);
    }

    public List<Guitar> findAll(){
        return  guitarRepository.findAll();
    }

    public  Guitar findById( long id)
    {
        return guitarRepository.findGuitarById(id);
    }

    public List<Guitar> findAllShowAndInQuality(){
        return  guitarRepository.findGuitarsByIsShowAndQuantityIsAfter(true , 0);}

    public List<Guitar> findAllShowAndInNoQuality(){
        return  guitarRepository.findGuitarsByIsShowAndQuantity(true , 0);}
}
