package org.example.application.service.entityService;

import org.example.entityManage.entity.OrderDetail;

import java.util.List;

public interface EntityOrderDetailService {

    OrderDetail saveOrderDetail(OrderDetail orderDetail);

    List<OrderDetail> saveOrderDetails(List<OrderDetail> orderDetail);

    OrderDetail getOrderDetail(String id);

    List<OrderDetail> getOrderDetailInOrder(String orderId);

    boolean isExist(String id);

    void deleteOrderDetail(String id);

    void deleteOrderDetailInOrder(String orderId);
}
