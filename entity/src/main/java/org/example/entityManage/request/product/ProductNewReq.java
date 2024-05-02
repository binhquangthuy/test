package org.example.entityManage.request.product;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductNewReq {

    private String id;

    @NotBlank(message = "name must is not blank")
    @Size(min = 1, max = 255, message = "Length name is must between 1 - 255")
    private String name;

    private String description;

    @Min(value = 0, message = "price must greater than 0")
    private Long price;

    @Min(value = 0, message = "price must greater than 0")
    private Long inventory;
}
