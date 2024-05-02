package org.example.application.service.logicService.impl;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.example.application.service.entityService.EntityOrderDetailService;
import org.example.application.service.entityService.EntityOrderService;
import org.example.application.service.entityService.EntityProductService;
import org.example.application.service.logicService.OrderService;
import org.example.entityManage.constant.StatusOrderEnum;
import org.example.entityManage.entity.Order;
import org.example.entityManage.entity.OrderDetail;
import org.example.entityManage.entity.Product;
import org.example.entityManage.mapper.OrderDetailMapper;
import org.example.entityManage.mapper.OrderMapper;
import org.example.entityManage.request.order.OrderNewReq;
import org.example.entityManage.request.order.OrderUpdateReq;
import org.example.entityManage.request.orderDetail.OrderDetailNewReq;
import org.example.entityManage.request.orderDetail.OrderDetailUpdateReq;
import org.example.entityManage.response.order.OrderResp;
import org.example.utility.constant.MessageCode;
import org.example.utility.exception.BadRequestException;
import org.example.utility.exception.DataNotFoundException;
import org.example.utility.util.CalculateUtils;
import org.example.utility.util.CheckUtils;
import org.example.utility.util.MessageResourceUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final EntityOrderService entityOrderService;
    private final EntityOrderDetailService entityOrderDetailService;
    private final EntityProductService entityProductService;
    private final OrderMapper orderMapper;
    private final OrderDetailMapper orderDetailMapper;
    @Override
    public OrderResp addOrder(OrderNewReq req) {
        Order order = orderMapper.orderFromReq(req);
        order = entityOrderService.saveOrder(order);

        final String orderId = order.getId();
        req.getOrderDetails().forEach(x-> {
            x.setOrderId(orderId);
            processOrderDetail(x);
        });

        List<OrderDetail> listDetail = orderDetailMapper.fromListDetailReq(req.getOrderDetails());
        listDetail = entityOrderDetailService.saveOrderDetails(listDetail);

        order.setPaidMoney(CalculateUtils.sumLongNumbers(req.getOrderDetails().stream().map(OrderDetailNewReq::getTotalMoney).toList()));
        OrderResp response = orderMapper.respFromOrder(order);
        response.setOrderDetails(orderDetailMapper.fromOrderDetailList(listDetail));
        return response;
    }

    private void processOrderDetail(OrderDetailNewReq detail) {
        Product product = updateProductOfOrder(detail);
        detail.setSoldPrice(product.getPrice());
        detail.setTotalMoney(product.getPrice() * detail.getQuality());
    }

    private Product updateProductOfOrder(OrderDetailNewReq detail) {
        Product product = entityProductService.getProduct(detail.getProductId());
        if (product == null) {
            throw new DataNotFoundException(MessageResourceUtil.getMessage(MessageCode.ERROR_02), "product");
        }
        if (CheckUtils.checkLongIsSmaller(product.getInventory(), detail.getQuality())) {
            throw new BadRequestException(MessageResourceUtil.getMessage(MessageCode.ERROR_03), "quantity");
        }
        if (detail.getSoldPrice() != null && Objects.equals(detail.getSoldPrice(), product.getPrice())) {
            throw new BadRequestException(MessageResourceUtil.getMessage(MessageCode.ERROR_03), "sold price");
        }
        product.setInventory(CalculateUtils.subLongNumbers(product.getInventory(), detail.getQuality()));
        return entityProductService.saveProduct(product);
    }

    @Override
    public OrderResp getOrder(String id) {
        Order order = entityOrderService.getOrder(id);
        if (order == null) {
            throw new DataNotFoundException(MessageResourceUtil.getMessage(MessageCode.ERROR_02));
        }
        List<OrderDetail> orderDetails = entityOrderDetailService.getOrderDetailInOrder(id);
        OrderResp resp = orderMapper.respFromOrder(order);
        resp.setOrderDetails(orderDetailMapper.fromOrderDetailList(orderDetails));
        return resp;
    }

    @Override
    public OrderResp updateOrder(OrderUpdateReq req) {
        Set<String> reqOrderId = req.getOrderDetails().stream().map(OrderDetailUpdateReq::getOrderId).collect(Collectors.toSet());
        reqOrderId.remove(null);
        if(reqOrderId.size() != 1 || !reqOrderId.contains(req.getId())) {
            throw new BadRequestException(MessageResourceUtil.getMessage(MessageCode.ERROR_04), "orderDetail orderId");
        }

        Order order = entityOrderService.getOrder(req.getId());
        if (order == null) {
            throw new DataNotFoundException(MessageResourceUtil.getMessage(MessageCode.ERROR_02), "order");
        }
        if (StatusOrderEnum.CANCEL.getValue().equals(order.getStatus())) {
            throw new BadRequestException(MessageResourceUtil.getMessage(MessageCode.ERROR_05), "order");
        }
        order.setNameCustomer(req.getNameCustomer());
        order.setAddress(req.getAddress());
        order.setEmail(req.getEmail());
        order.setPhoneNumber(req.getPhoneNumber());
        order.setStatus(req.getStatus());
        entityOrderService.saveOrder(order);

        List<OrderDetail> orderDetails = entityOrderDetailService.getOrderDetailInOrder(req.getId());
        if (StatusOrderEnum.CANCEL.getValue().equals(req.getStatus())) {
            cancelOrder(orderDetails);
            return orderMapper.respFromOrder(order);
        }


        if (CollectionUtils.isEmpty(orderDetails)) {
            throw new DataNotFoundException(MessageResourceUtil.getMessage(MessageCode.ERROR_02), "orderDetails");
        }
        List<OrderDetail> saved = updateOrderDetail(orderDetails, req.getOrderDetails());
        order.setPaidMoney(CalculateUtils.sumLongNumbers(req.getOrderDetails().stream().map(OrderDetailUpdateReq::getTotalMoney).toList()));
        entityOrderService.saveOrder(order);

        OrderResp response = orderMapper.respFromOrder(order);
        response.setOrderDetails(orderDetailMapper.fromOrderDetailList(saved));
        return response;
    }

    private void cancelOrder(List<OrderDetail> orderDetails) {
        for (OrderDetail detail : orderDetails) {
            Product product = entityProductService.getProduct(detail.getProductId());
            if (product == null) {
                continue;
            }
            product.setInventory(CalculateUtils.sumLongNumbers(product.getInventory(), detail.getQuality()));
            entityProductService.saveProduct(product);
        }
    }

    private List<OrderDetail> updateOrderDetail(List<OrderDetail> orderDetails, List<OrderDetailUpdateReq> updateDetails) {
        List<String> updateIds = updateDetails.stream().map(OrderDetailUpdateReq::getId).toList();
        List<String> availableIds = orderDetails.stream().map(OrderDetail::getId).toList();
        Map<String, OrderDetail> orderDetailMap = orderDetails.stream().collect(Collectors.toMap(OrderDetail::getId, o -> o));
        List<OrderDetail> result = new ArrayList<>();

        // update delete product in order detail
        List<String> deleteId = availableIds.stream().filter(x->!updateIds.contains(x)).toList();
        deleteId.forEach(x-> {
            deleteOrderDetailInOrder(orderDetailMap.get(x));
        });

        for (OrderDetailUpdateReq req : updateDetails) {
            if (StringUtils.isBlank(req.getId())) {
                // update add new product in order detail
                updateAddingProductForOrder(req);
                result.add(entityOrderDetailService.saveOrderDetail(orderDetailMapper.orderDetailFromUpdateReq(req)));
            } else if (availableIds.contains(req.getId())) {
                // update modify quantity product order detail
                updateOldProductInOrder(orderDetailMap.get(req.getId()), req);
                OrderDetail orderDetail = entityOrderDetailService.getOrderDetail(orderDetailMap.get(req.getId()).getId());
                orderDetail.setQuality(req.getQuality());
                result.add(entityOrderDetailService.saveOrderDetail(orderDetail));
            } else {
                throw new DataNotFoundException(MessageResourceUtil.getMessage(MessageCode.ERROR_02), "orderDetail id");
            }
        }


        return result;
    }



    private void updateOldProductInOrder(OrderDetail orderDetail, OrderDetailUpdateReq detailUpdate) {
        if (!Objects.equals(orderDetail.getProductId(), detailUpdate.getProductId())
        || !Objects.equals(orderDetail.getOrderId(), detailUpdate.getOrderId())
        || !Objects.equals(orderDetail.getSoldPrice(), detailUpdate.getSoldPrice())) {
            throw new BadRequestException(MessageResourceUtil.getMessage(MessageCode.ERROR_04), "orderDetail");
        }
        Long oldQuantity = orderDetail.getQuality();
        Long updateQuantity = detailUpdate.getQuality();
        Long delta = CalculateUtils.subLongNumbers(updateQuantity, oldQuantity);

        if (Objects.equals(oldQuantity, updateQuantity)) {
            return;
        }

        Product product = entityProductService.getProduct(orderDetail.getProductId());
        if (product == null) {
            throw new DataNotFoundException(MessageResourceUtil.getMessage(MessageCode.ERROR_02), "product");
        }
        Long quantity = CalculateUtils.sumLongNumbers(product.getInventory(), delta);
        if (CheckUtils.checkLongIsSmaller(product.getInventory(), delta)) {
            throw new BadRequestException(MessageResourceUtil.getMessage(MessageCode.ERROR_03), "quantity");
        }
        product.setInventory(quantity);
        entityProductService.saveProduct(product);

        detailUpdate.setTotalMoney(detailUpdate.getQuality() * orderDetail.getQuality());
    }

    private void updateAddingProductForOrder(OrderDetailUpdateReq detailUpdate) {
        Product product = updateProductOfOrder(orderDetailMapper.fromOrderDetailUpdateReq(detailUpdate));
        detailUpdate.setTotalMoney(detailUpdate.getQuality() * product.getPrice());
    }

    private void deleteOrderDetailInOrder(OrderDetail orderDetail) {
        Product product = entityProductService.getProduct(orderDetail.getProductId());
        if (product == null) {
            throw new DataNotFoundException(MessageResourceUtil.getMessage(MessageCode.ERROR_02), "product");
        }
        product.setInventory(CalculateUtils.sumLongNumbers(product.getInventory(), orderDetail.getQuality()));
        entityProductService.saveProduct(product);
    }

    @Override
    public void deleteOrder(String id) {
        if (!entityOrderService.isExist(id)) {
            throw new DataNotFoundException(MessageResourceUtil.getMessage(MessageCode.ERROR_02));
        }
        entityOrderService.deleteOrder(id);
        entityOrderDetailService.deleteOrderDetailInOrder(id);
    }
}
