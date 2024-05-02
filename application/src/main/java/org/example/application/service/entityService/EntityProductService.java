package org.example.application.service.entityService;

import org.example.entityManage.dto.product.ProductSearchedDto;
import org.example.entityManage.entity.Product;
import org.example.entityManage.request.product.ProductSearchReq;

import java.util.List;

public interface EntityProductService {
    Product saveProduct(Product product);

    Product getProduct(String id);

    boolean isExist(String id);

    void deleteProduct(String id);

    List<ProductSearchedDto> searchProduct(ProductSearchReq paramDto);

    Integer countSearchProduct(ProductSearchReq paramDto);
}
