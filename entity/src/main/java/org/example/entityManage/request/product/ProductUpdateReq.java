package org.example.entityManage.request.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateReq {
    @NotNull(message = "id can not null")
    private String id;

    private String description;

    @Min(value = 0, message = "price min is 0")
    @NotNull(message = "price can not null")
    private Long price;

}
