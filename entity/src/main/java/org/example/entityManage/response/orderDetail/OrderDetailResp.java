package org.example.entityManage.response.orderDetail;

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
public class OrderDetailResp {

    private String id;

    private String orderId;

    private String productId;

    private Long soldPrice;

    private Integer quality;

}
