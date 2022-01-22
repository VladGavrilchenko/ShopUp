package com.example.ShopUp.Repository;


import com.example.ShopUp.User;
import com.example.ShopUp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {


    List<Order> findOrdersByIsProcess(boolean process);

    List<Order> findOrdersByIsProcessAndIsDone(boolean process ,boolean done);
    Order findOrderByIsProcessAndUser(boolean process , User user);

    List<Order> findOrdersByUser(User user);
}
