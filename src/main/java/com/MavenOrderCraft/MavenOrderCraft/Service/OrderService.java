package com.MavenOrderCraft.MavenOrderCraft.Service;

import com.MavenOrderCraft.MavenOrderCraft.Entitiy.Item;
import com.MavenOrderCraft.MavenOrderCraft.Entitiy.Order;
import com.MavenOrderCraft.MavenOrderCraft.Mapper.ItemMapper;
import com.MavenOrderCraft.MavenOrderCraft.Mapper.OrderMapper;
import com.MavenOrderCraft.MavenOrderCraft.Repository.OrderRepository;
import com.MavenOrderCraft.MavenOrderCraft.Repository.UserRepository;
import com.MavenOrderCraft.MavenOrderCraft.Request.OrderRequest;
import com.MavenOrderCraft.MavenOrderCraft.Response.ItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ItemService itemService;

    @Autowired
    public OrderService(OrderRepository orderRepository, ItemService itemService) {
        this.orderRepository = orderRepository;
        this.itemService = itemService;
    }

    @Transactional
    public List<Order> saveAll(List<OrderRequest> orderRequestList) {
        List<Order> orderList = getOrderList(orderRequestList);
        return orderRepository.saveAll(orderList);
    }

    private List<Order> getOrderList(List<OrderRequest> orderRequestList) {
        List<Order> orderList  = new ArrayList<>();
        for(int i=0; i<orderRequestList.size(); i++){
            Order o = new Order();
            OrderRequest request = orderRequestList.get(i);
            o.setQuantity(request.getQuantity());
            o.setItem_id(request.getItem_id());
            orderList.add(o);
        }
        return orderList;
    }
}
