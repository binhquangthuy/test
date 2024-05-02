package org.example.application.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.application.service.logicService.OrderService;
import org.example.entityManage.request.order.OrderNewReq;
import org.example.entityManage.request.order.OrderUpdateReq;
import org.example.entityManage.request.product.ProductUpdateReq;
import org.example.entityManage.response.order.OrderResp;
import org.example.entityManage.response.product.ProductResp;
import org.example.utility.core.GeneralResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("")
    public GeneralResponse<OrderResp> addOrder(@RequestBody @Valid OrderNewReq req) {
        return new GeneralResponse(orderService.addOrder(req));
    }

    @GetMapping("/{id}")
    public GeneralResponse<OrderResp> viewOrder(@PathVariable String id) {
        return new GeneralResponse(orderService.getOrder(id));
    }

    @PutMapping("")
    public GeneralResponse<OrderResp> updateOrder(@RequestBody @Valid OrderUpdateReq req) {
        return new GeneralResponse(orderService.updateOrder(req));
    }


}
