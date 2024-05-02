package org.example.application.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.application.service.logicService.ProductService;
import org.example.entityManage.dto.product.ProductSearchedDto;
import org.example.entityManage.request.product.ProductNewReq;
import org.example.entityManage.request.product.ProductSearchReq;
import org.example.entityManage.request.product.ProductUpdateReq;
import org.example.entityManage.response.product.ProductResp;
import org.example.utility.core.GeneralResponse;
import org.example.utility.core.ListResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("")
    public GeneralResponse<ProductResp> addProduct(@RequestBody ProductNewReq req) {
        return new GeneralResponse(productService.addProduct(req));
    }

    @GetMapping("/{id}")
    public GeneralResponse<ProductResp> viewProduct(@PathVariable String id) {
        return new GeneralResponse(productService.getProduct(id));
    }

    @PutMapping("")
    public GeneralResponse<ProductResp> updateProduct(@RequestBody @Valid ProductUpdateReq req) {
        return new GeneralResponse(productService.updateProduct(req));
    }

    @DeleteMapping("/{id}")
    public GeneralResponse<ProductResp> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return new GeneralResponse(true);
    }

    @GetMapping("/search")
    public ListResponse<ProductSearchedDto> searchProduct(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize) {
        return productService.searchProduct(new ProductSearchReq(name, description, page, pageSize));
    }
}
