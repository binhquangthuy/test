package org.example.application.service.logicService;

import org.example.entityManage.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {

    List<OrderDetail> addOrderDetails(List<OrderDetail> orderDetails);

    OrderDetail getOrderDetail(String id);

    void deleteOrderDetail(String id);
}
