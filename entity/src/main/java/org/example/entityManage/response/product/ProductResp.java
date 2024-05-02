package org.example.entityManage.response.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResp {
    private String id;

    private String name;

    private String description;

    private Long price;

    private Long inventory;
}
