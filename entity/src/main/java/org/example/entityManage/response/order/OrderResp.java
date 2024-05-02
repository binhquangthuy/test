package org.example.entityManage.response.order;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.entityManage.request.orderDetail.OrderDetailNewReq;
import org.example.entityManage.response.orderDetail.OrderDetailResp;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResp {
    private String id;

    private String nameCustomer;

    private String address;

    private String email;

    private String phoneNumber;

    private Integer status;

    private Long paidMoney;

    private List<OrderDetailResp> orderDetails;
}
