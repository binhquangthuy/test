package org.example.application.repository.customRepo;

import org.example.entityManage.dto.product.ProductSearchedDto;
import org.example.entityManage.request.product.ProductSearchReq;

import java.util.List;

public interface ProductCustomRepo {
    List<ProductSearchedDto> searchProducts(ProductSearchReq paramDto);

    Integer countSearchProducts(ProductSearchReq paramDto);
}
