package com.example.ShopUp.service;


import com.example.ShopUp.Repository.GuitarStringRepository;
import com.example.ShopUp.model.GuitarString;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuitarStringsService {

    private GuitarStringRepository guitarStringRepository;

    GuitarStringsService( GuitarStringRepository guitarStringRepository) {
        this.guitarStringRepository = guitarStringRepository;
    }

    public List<GuitarString> getAll(){
        return guitarStringRepository.findAll();
    }


    public GuitarString getById(long id){
       return guitarStringRepository.findGuitarStringsById(id);
    }

    public void save(GuitarString guitarString){
        guitarStringRepository.save(guitarString);
    }

    public List<GuitarString> findAllShowAndInQuality(){
        return  guitarStringRepository.findGuitarStringsByIsShowAndQuantityIsAfter(true , 0);}

    public List<GuitarString> findAllShowAndInNoQuality(){
        return  guitarStringRepository.findGuitarStringsByIsShowAndQuantity(true , 0);}

}
