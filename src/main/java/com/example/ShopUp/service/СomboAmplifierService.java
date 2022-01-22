package com.example.ShopUp.service;

import com.example.ShopUp.Repository.СomboAmplifierRepository;

import com.example.ShopUp.model.ComboAmplifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class СomboAmplifierService {

    private СomboAmplifierRepository comboAmplifierRepository;

    public СomboAmplifierService(СomboAmplifierRepository comboAmplifierRepository) {
        this.comboAmplifierRepository= comboAmplifierRepository;
    }

    public  void save(ComboAmplifier comboAmplifier) {
        comboAmplifierRepository.save(comboAmplifier);
    }

    public List<ComboAmplifier> getAll()
    {
        return  comboAmplifierRepository.findAll();
    }

    public ComboAmplifier getById(long id){return  comboAmplifierRepository.findСomboAmplifierById(id);}


    public List<ComboAmplifier> findAllShowAndInQuality(){
        return  comboAmplifierRepository.findСomboAmplifiersByIsShowAndQuantityIsAfter(true , 0);}

    public List<ComboAmplifier> findAllShowAndInNoQuality(){
        return  comboAmplifierRepository.findСomboAmplifiersByIsShowAndQuantity(true , 0);}

}
