package com.example.ShopUp.service;

import com.example.ShopUp.Repository.OrderRepository;
import com.example.ShopUp.User;
import com.example.ShopUp.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    OrderService(OrderRepository orderRepository)
    {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrdersNotProgres(){
        return  this.orderRepository.findOrdersByIsProcess(false);
    }

    public List<Order> getAllOrdersProgres(){
        return  this.orderRepository.findOrdersByIsProcess(true);
    }

    public Order getOrderById(long id)
    {
        return   orderRepository.getById(id);
    }

    public void save(Order order)
    {
        orderRepository.save(order);
    }

    public  List<Order> getAllOrdersOnProgresNotDone(){
        return  orderRepository.findOrdersByIsProcessAndIsDone(false , false);
    }

    public  List<Order> getAllOrdersByUser(User user){
        return  orderRepository.findOrdersByUser(user);
    }

    public  List<Order> getAllOrdersOnProgresDone(){
        return  orderRepository.findOrdersByIsProcessAndIsDone(false , true);
    }

    public Order getOrderOnProgresAndUser(User user){
        return orderRepository.findOrderByIsProcessAndUser(true , user);
    }
}
