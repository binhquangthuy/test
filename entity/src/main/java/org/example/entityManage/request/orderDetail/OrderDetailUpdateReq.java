package org.example.entityManage.request.orderDetail;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailUpdateReq {

    private String id;

    @NotBlank(message = "order id must be not blank")
    private String orderId;

    @NotBlank(message = "product id must be not blank")
    private String productId;

    @Min(value = 0, message = "soldPrice must greater than 0")
    private Long soldPrice;

    @Min(value = 0, message = "quantity must greater than 0")
    private Long quality;

    @JsonIgnore
    private Long totalMoney;
}
