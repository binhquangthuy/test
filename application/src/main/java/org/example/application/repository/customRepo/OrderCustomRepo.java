package org.example.application.repository.customRepo;

import org.example.entityManage.dto.order.OrderSearchedDto;
import org.example.entityManage.request.order.OrderSearchReq;
import org.example.entityManage.entity.Order;
import org.example.entityManage.request.product.ProductSearchReq;

import java.util.List;

public interface OrderCustomRepo {
    List<OrderSearchedDto> searchOrders(OrderSearchReq keyword);

    Integer countSearchOrders(OrderSearchReq paramDto);
}
