package org.example.entityManage.mapper;

import org.example.entityManage.entity.Product;
import org.example.entityManage.request.product.ProductNewReq;
import org.example.entityManage.request.product.ProductUpdateReq;
import org.example.entityManage.response.product.ProductResp;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product productFromReq(ProductNewReq req);

    ProductResp productRespFromEntity(Product product);

    Product productFromUpdateReq(ProductUpdateReq req);
}
