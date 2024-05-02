package org.example.application.service.logicService.impl;

import lombok.RequiredArgsConstructor;
import org.example.application.repository.OrderDetailRepository;
import org.example.application.service.logicService.OrderDetailService;
import org.example.entityManage.entity.OrderDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepo;
    @Override
    public List<OrderDetail> addOrderDetails(List<OrderDetail> orderDetails) {
        return orderDetailRepo.saveAll(orderDetails);
    }

    @Override
    public OrderDetail getOrderDetail(String id) {
        return orderDetailRepo.findById(id).orElse(null);
    }


    @Override
    public void deleteOrderDetail(String id) {
        orderDetailRepo.deleteById(id);
    }
}
