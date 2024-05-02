package org.example.entityManage.mapper;

import org.example.entityManage.entity.OrderDetail;
import org.example.entityManage.request.orderDetail.OrderDetailNewReq;
import org.example.entityManage.request.orderDetail.OrderDetailUpdateReq;
import org.example.entityManage.response.orderDetail.OrderDetailResp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {

    OrderDetail orderDetailFromReq(OrderDetailNewReq req);

    List<OrderDetail> fromListDetailReq(List<OrderDetailNewReq> listReq);

    OrderDetailResp fromOrderDetail(OrderDetail orderDetail);

    List<OrderDetailResp> fromOrderDetailList(List<OrderDetail> orderDetails);

    OrderDetailNewReq fromOrderDetailUpdateReq(OrderDetailUpdateReq req);

    OrderDetail orderDetailFromUpdateReq(OrderDetailUpdateReq req);
}
