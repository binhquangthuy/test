package org.example.application.service.entityService.impl;

import lombok.RequiredArgsConstructor;
import org.example.application.repository.OrderDetailRepository;
import org.example.application.service.entityService.EntityOrderDetailService;
import org.example.entityManage.entity.OrderDetail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EntityOrderDetailServiceImpl implements EntityOrderDetailService {

    private final OrderDetailRepository orderDetailRepo;

    @Override
    public OrderDetail saveOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepo.save(orderDetail);
    }

    @Override
    public List<OrderDetail> saveOrderDetails(List<OrderDetail> orderDetail) {
        return orderDetailRepo.saveAll(orderDetail);
    }

    @Override
    public OrderDetail getOrderDetail(String id) {
        return orderDetailRepo.findById(id).orElse(null);
    }

    @Override
    public List<OrderDetail> getOrderDetailInOrder(String orderId) {
        return orderDetailRepo.findAllByOrderId(orderId);
    }

    @Override
    public boolean isExist(String id) {
        return orderDetailRepo.existsById(id);
    }

    @Override
    public void deleteOrderDetail(String id) {
        orderDetailRepo.deleteById(id);
    }

    @Override
    public void deleteOrderDetailInOrder(String orderId) {
        orderDetailRepo.deleteAllByOrderId(orderId);
    }
}
