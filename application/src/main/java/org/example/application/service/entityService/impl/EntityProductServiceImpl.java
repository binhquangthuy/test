package org.example.application.service.entityService.impl;

import lombok.RequiredArgsConstructor;
import org.example.application.repository.ProductRepository;
import org.example.application.repository.customRepo.ProductCustomRepo;
import org.example.application.service.entityService.EntityProductService;
import org.example.entityManage.dto.product.ProductSearchedDto;
import org.example.entityManage.entity.Product;
import org.example.entityManage.request.product.ProductSearchReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntityProductServiceImpl implements EntityProductService {

    private final ProductRepository productRepository;

    private final ProductCustomRepo productCustomRepo;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProduct(String id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public boolean isExist(String id) {
        return productRepository.existsById(id);
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductSearchedDto> searchProduct(ProductSearchReq paramDto) {
        return productCustomRepo.searchProducts(paramDto);
    }

    @Override
    public Integer countSearchProduct(ProductSearchReq paramDto) {
        return productCustomRepo.countSearchProducts(paramDto);
    }
}
