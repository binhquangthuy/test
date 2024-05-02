package org.example.application.service.logicService.impl;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.example.application.service.entityService.EntityProductService;
import org.example.application.service.logicService.ProductService;
import org.example.entityManage.dto.product.ProductSearchedDto;
import org.example.entityManage.mapper.ProductMapper;
import org.example.entityManage.request.product.ProductNewReq;
import org.example.entityManage.request.product.ProductSearchReq;
import org.example.entityManage.entity.Product;
import org.example.entityManage.request.product.ProductUpdateReq;
import org.example.entityManage.response.product.ProductResp;
import org.example.utility.util.CalculateUtils;
import org.example.utility.util.CheckUtils;
import org.example.utility.constant.MessageCode;
import org.example.utility.core.ListResponse;
import org.example.utility.exception.BadRequestException;
import org.example.utility.exception.DataNotFoundException;
import org.example.utility.util.MessageResourceUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final EntityProductService entityProductService;

    private final ProductMapper productMapper;

    @Override
    public ProductResp addProduct(ProductNewReq req) {
        Product product;
        if (StringUtils.isBlank(req.getId())) {
            product = entityProductService.saveProduct(productMapper.productFromReq(req));
        } else if (CheckUtils.isPositiveNumber(req.getInventory())) {
            product = entityProductService.getProduct(req.getId());
            if (product == null) {
                throw new DataNotFoundException(MessageResourceUtil.getMessage(MessageCode.ERROR_02), "product");
            }
            product.setInventory(CalculateUtils.sumLongNumbers(product.getInventory(), req.getInventory()));
            product = entityProductService.saveProduct(product);
        } else {
            throw new BadRequestException(MessageResourceUtil.getMessage(MessageCode.ERROR_01), "inventory");
        }

        return productMapper.productRespFromEntity(product);
    }

    @Override
    public ProductResp getProduct(String id) {
        Product product = entityProductService.getProduct(id);
        if (product == null) {
            throw new DataNotFoundException(MessageResourceUtil.getMessage(MessageCode.ERROR_02));
        }
        return productMapper.productRespFromEntity(product);
    }

    @Override
    public ProductResp updateProduct(ProductUpdateReq product) {
        Product modify = entityProductService.getProduct(product.getId());
        if (modify == null) {
            throw new DataNotFoundException(MessageResourceUtil.getMessage(MessageCode.ERROR_02));
        }
        modify.setDescription(product.getDescription());
        modify.setPrice(product.getPrice());
        modify = entityProductService.saveProduct(modify);
        return productMapper.productRespFromEntity(modify);
    }

    @Override
    public void deleteProduct(String id) {
        if (!entityProductService.isExist(id)) {
            throw new DataNotFoundException(MessageResourceUtil.getMessage(MessageCode.ERROR_02));
        }
        entityProductService.deleteProduct(id);
    }

    @Override
    public ListResponse<ProductSearchedDto> searchProduct(ProductSearchReq paramDto) {
        Integer total = entityProductService.countSearchProduct(paramDto);
        if (total == null || total <= 0) {
            new ListResponse(new ArrayList<>(), 0);
        }
        List<ProductSearchedDto> listData = entityProductService.searchProduct(paramDto);

        return new ListResponse(listData, total);
    }

}
