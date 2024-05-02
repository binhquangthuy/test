package org.example.application.repository;

import org.example.entityManage.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    List<OrderDetail> findAllByOrderId(String orderId);

    void deleteAllByOrderId(String orderId);
}
