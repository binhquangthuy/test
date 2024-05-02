package org.example.application.service.logicService;

import org.example.entityManage.dto.product.ProductSearchedDto;
import org.example.entityManage.request.product.ProductNewReq;
import org.example.entityManage.request.product.ProductSearchReq;
import org.example.entityManage.request.product.ProductUpdateReq;
import org.example.entityManage.response.product.ProductResp;
import org.example.utility.core.ListResponse;

public interface ProductService {
    ProductResp addProduct(ProductNewReq product);

    ProductResp getProduct(String id);

    ProductResp updateProduct(ProductUpdateReq product);

    void deleteProduct(String id);

    ListResponse<ProductSearchedDto> searchProduct(ProductSearchReq paramDto);
}
