package org.example.entityManage.request.order;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.entityManage.request.orderDetail.OrderDetailNewReq;
import org.example.entityManage.request.orderDetail.OrderDetailUpdateReq;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdateReq {

    @NotBlank(message = "order id must be not blank")
    private String id;

    @NotBlank(message = "customer name must be not blank")
    private String nameCustomer;

    @NotBlank(message = "address must be not blank")
    private String address;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "phone number must be not blank")
    @Size(min = 10, max = 10, message = "Length phone number be must 10")
    private String phoneNumber;

    @Min(value = 1, message = "not support this status order, only 1 or 2")
    @Max(value = 3, message = "not support this status order, only 1 or 2")
    private Integer status;

    @NotNull(message = "order must not be empty")
    private List<OrderDetailUpdateReq> orderDetails;

}
