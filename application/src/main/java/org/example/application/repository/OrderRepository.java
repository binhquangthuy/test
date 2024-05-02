package org.example.application.repository;

import org.example.entityManage.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    @Query("SELECT o FROM Order o WHERE o.id like :keyword OR o.nameCustomer like :keyword ORDER BY o.createdDate DESC")
    List<Order> searchOrders(String keyword);
}
