package org.example.application.service.entityService;

import org.example.entityManage.dto.order.OrderSearchedDto;
import org.example.entityManage.request.order.OrderSearchReq;
import org.example.entityManage.entity.Order;

import java.util.List;

public interface EntityOrderService {

    Order saveOrder(Order order);

    Order getOrder(String id);

    boolean isExist(String id);

    void deleteOrder(String id);

    List<OrderSearchedDto> searchOrders(OrderSearchReq paramDto);

    Integer countSearchOrders(OrderSearchReq paramDto);
}
