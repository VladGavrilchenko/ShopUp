package com.example.ShopUp.service;


import com.example.ShopUp.Repository.MediatorRepository;
import com.example.ShopUp.model.Mediator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediatorService {

    private MediatorRepository mediatorRepository;

    MediatorService(MediatorRepository mediatorRepository) {
        this.mediatorRepository = mediatorRepository;
    }



    public void saveMediator(Mediator mediator){
        mediatorRepository.save(mediator);
    }

    public List<Mediator> findAll(){
        return  mediatorRepository.findAll();
    }

    public  Mediator findById( long id)
    {
        return mediatorRepository.findMediatorById(id);
    }

    public List<Mediator> findAllShowAndInQuality(){
        return  mediatorRepository.findMediatorsByIsShowAndQuantityIsAfter(true , 0);}

    public List<Mediator> findAllShowAndInNoQuality(){
        return  mediatorRepository.findMediatorsByIsShowAndQuantity(true , 0);}
}
