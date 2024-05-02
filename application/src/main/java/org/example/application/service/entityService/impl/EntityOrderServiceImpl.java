package org.example.application.service.entityService.impl;

import lombok.RequiredArgsConstructor;
import org.example.application.repository.OrderRepository;
import org.example.application.repository.customRepo.OrderCustomRepo;
import org.example.application.service.entityService.EntityOrderService;
import org.example.entityManage.dto.order.OrderSearchedDto;
import org.example.entityManage.request.order.OrderSearchReq;
import org.example.entityManage.entity.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EntityOrderServiceImpl implements EntityOrderService {

    private final OrderRepository orderRepository;

    private final OrderCustomRepo orderCustomRepo;

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOrder(String id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public boolean isExist(String id) {
        return orderRepository.existsById(id);
    }

    @Override
    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderSearchedDto> searchOrders(OrderSearchReq paramDto) {
        return orderCustomRepo.searchOrders(paramDto);
    }

    @Override
    public Integer countSearchOrders(OrderSearchReq paramDto) {
        return orderCustomRepo.countSearchOrders(paramDto);
    }
}
