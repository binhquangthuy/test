package org.example.entityManage.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.entityManage.util.ParserUtils;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSearchedDto {
    private String id;

    private String name;

    private String description;

    private Long price;

    private Long inventory;

    public ProductSearchedDto(Object[] fields) {
        this.id = ParserUtils.parseObjectToString(fields[0]);
        this.name = ParserUtils.parseObjectToString(fields[1]);
        this.description = ParserUtils.parseObjectToString(fields[2]);
        this.price = ParserUtils.parseObjectToLong(fields[3]);
        this.inventory = ParserUtils.parseObjectToLong(fields[4]);
    }
}
