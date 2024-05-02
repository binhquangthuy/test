package org.example.application.repository.impl;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.example.application.repository.customRepo.OrderCustomRepo;
import org.example.entityManage.dto.order.OrderSearchedDto;
import org.example.entityManage.dto.product.ProductSearchedDto;
import org.example.entityManage.request.order.OrderSearchReq;
import org.example.entityManage.util.ParserUtils;
import org.example.utility.util.SqlUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderCustomRepoImpl implements OrderCustomRepo {
    private final EntityManager entityManager;

    @Override
    public List<OrderSearchedDto> searchOrders(OrderSearchReq paramDto) {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT o.id, o.createdDate, o.customerName, o.address, o.email, o.phoneNumber, o.status, o.paidMoney ");
        sql.append("FROM Order o ");
        sql.append(setConditionSearchOrder(paramDto));

        Query q = entityManager.createQuery(sql.toString());
        setParamToQueryConditionSearchOrder(q, paramDto);
        List<Object[]> requestListRaw = q.setFirstResult(SqlUtils.getOffsetQuery(paramDto.getPage(), paramDto.getPageSize()))
                .setMaxResults(paramDto.getPageSize()).getResultList();
        List<OrderSearchedDto> listOrder = new ArrayList<>();
        if (!CollectionUtils.isEmpty(requestListRaw)) {
            for (Object[] item : requestListRaw) {
                OrderSearchedDto newItem = new OrderSearchedDto(item);
                listOrder.add(newItem);
            }
        }

        return listOrder;
    }

    @Override
    public Integer countSearchOrders(OrderSearchReq paramDto) {
        StringBuilder sqlForQuantity = new StringBuilder();
        sqlForQuantity.append("SELECT COUNT(*) ");
        sqlForQuantity.append("FROM Order o ");
        sqlForQuantity.append(setConditionSearchOrder(paramDto));
        Query qForQuantity = entityManager.createQuery(sqlForQuantity.toString());
        setParamToQueryConditionSearchOrder(qForQuantity, paramDto);
        List<Object[]> resultQueryForQuantity = qForQuantity.getResultList();

        if (resultQueryForQuantity == null || CollectionUtils.isEmpty(resultQueryForQuantity)) {
            return null;
        }

        return ParserUtils.parseObjectToInteger(resultQueryForQuantity.get(0));

    }

    private String setConditionSearchOrder(OrderSearchReq paramDto) {
        StringBuilder content = new StringBuilder();
        if (paramDto != null) {
            content.append(" WHERE (1=1) ");
            if (StringUtils.isNotBlank(paramDto.getId())) {
                content.append(" AND o.id LIKE :id");
            }
            if (StringUtils.isNotBlank(paramDto.getCustomerName())) {
                content.append(" AND o.customerName LIKE :customerName");
            }
        }
        return content.toString();
    }

    private void setParamToQueryConditionSearchOrder(Query q, OrderSearchReq paramDto) {
        if (paramDto != null) {
            if (StringUtils.isNotBlank(paramDto.getId())) {
                q.setParameter("id", paramDto.getId() + "%");
            }
            if (StringUtils.isNotBlank(paramDto.getCustomerName())) {
                q.setParameter("customerName", paramDto.getCustomerName() + "%");
            }
        }
    }
}