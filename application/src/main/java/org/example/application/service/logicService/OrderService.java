package org.example.application.service.logicService;

import org.example.entityManage.entity.Order;
import org.example.entityManage.request.order.OrderNewReq;
import org.example.entityManage.request.order.OrderUpdateReq;
import org.example.entityManage.response.order.OrderResp;

public interface OrderService {

    OrderResp addOrder(OrderNewReq order);

    OrderResp getOrder(String id);

    OrderResp updateOrder(OrderUpdateReq order);

    void deleteOrder(String id);
}
