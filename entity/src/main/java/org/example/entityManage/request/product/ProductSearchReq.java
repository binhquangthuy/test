package org.example.entityManage.request.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.entityManage.dto.Page;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSearchReq extends Page {
    private String name;
    private String description;

    public ProductSearchReq(String name, String description, Integer page, Integer pageSize) {
        super(page, pageSize);
        this.name = name;
        this.description = description;
    }
}
