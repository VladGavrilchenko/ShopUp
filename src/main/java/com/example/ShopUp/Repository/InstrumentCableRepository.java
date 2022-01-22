package com.example.ShopUp.Repository;


import com.example.ShopUp.model.InstrumentCable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstrumentCableRepository extends JpaRepository<InstrumentCable ,Long> {
    InstrumentCable findInstrumentCableById(long id);
    List<InstrumentCable> findInstrumentCablesByIsShowAndQuantity(boolean show , int quantity);
    List<InstrumentCable> findInstrumentCablesByIsShowAndQuantityIsAfter(boolean show , int quantity);
}
