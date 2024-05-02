package org.example.application.repository.impl;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.example.application.repository.customRepo.ProductCustomRepo;
import org.example.entityManage.dto.product.ProductSearchedDto;
import org.example.entityManage.request.product.ProductSearchReq;
import org.example.entityManage.util.ParserUtils;
import org.example.utility.util.SqlUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductCustomRepoImpl implements ProductCustomRepo {

    private final EntityManager entityManager;

    @Override
    public List<ProductSearchedDto> searchProducts(ProductSearchReq paramDto) {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p.id, p.name, p.description, p.price, p.inventory ");
        sql.append("FROM Product p ");
        sql.append(setConditionSearchProduct(paramDto));

        Query q = entityManager.createQuery(sql.toString());
        setParamToQueryConditionSearchProduct(q, paramDto);
        List<Object[]> requestListRaw = q.setFirstResult(SqlUtils.getOffsetQuery(paramDto.getPage(), paramDto.getPageSize()))
                .setMaxResults(paramDto.getPageSize()).getResultList();
        List<ProductSearchedDto> listProduct = new ArrayList<>();
        if (!CollectionUtils.isEmpty(requestListRaw)) {
            for (Object[] item : requestListRaw) {
                ProductSearchedDto newItem = new ProductSearchedDto(item);
                listProduct.add(newItem);
            }
        }

        return listProduct;
    }

    @Override
    public Integer countSearchProducts(ProductSearchReq paramDto) {
        StringBuilder sqlForQuantity = new StringBuilder();
        sqlForQuantity.append("SELECT COUNT(*) ");
        sqlForQuantity.append("FROM Product p ");
        sqlForQuantity.append(setConditionSearchProduct(paramDto));
        Query qForQuantity = entityManager.createQuery(sqlForQuantity.toString());
        setParamToQueryConditionSearchProduct(qForQuantity, paramDto);
        List<Object[]> resultQueryForQuantity = qForQuantity.getResultList();

        if (resultQueryForQuantity == null || CollectionUtils.isEmpty(resultQueryForQuantity)) {
            return null;
        }

        return ParserUtils.parseObjectToInteger(resultQueryForQuantity.get(0));

    }

    private String setConditionSearchProduct(ProductSearchReq paramDto) {
        StringBuilder content = new StringBuilder();
        if (paramDto != null) {
            content.append(" WHERE (1=1) ");
            if (StringUtils.isNotBlank(paramDto.getName())) {
                content.append(" AND p.name LIKE :name");
            }
            if (StringUtils.isNotBlank(paramDto.getDescription())) {
                content.append(" AND p.description LIKE :description");
            }
        }
        return content.toString();
    }

    private void setParamToQueryConditionSearchProduct(Query q, ProductSearchReq paramDto) {
        if (paramDto != null) {
            if (StringUtils.isNotBlank(paramDto.getName())) {
                q.setParameter("name", paramDto.getName() + "%");
            }
            if (StringUtils.isNotBlank(paramDto.getDescription())) {
                q.setParameter("description", paramDto.getDescription() + "%");
            }
        }
    }

}
