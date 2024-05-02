package org.example.entityManage.mapper;

import org.example.entityManage.entity.Order;
import org.example.entityManage.entity.OrderDetail;
import org.example.entityManage.entity.Product;
import org.example.entityManage.request.order.OrderNewReq;
import org.example.entityManage.request.product.ProductNewReq;
import org.example.entityManage.request.product.ProductUpdateReq;
import org.example.entityManage.response.order.OrderResp;
import org.example.entityManage.response.product.ProductResp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order orderFromReq(OrderNewReq req);

    OrderResp respFromOrder(Order order);

//    @Mapping(target = "orderDetails", source = "orderDetails", qualifiedByName="mapOrderDetails")
//    OrderResp respFromOrder(Order order, List<OrderDetail> orderDetails);
//
//    @Named("mapOrderDetails")
}
