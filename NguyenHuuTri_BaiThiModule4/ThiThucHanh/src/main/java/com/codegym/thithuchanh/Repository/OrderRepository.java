package com.codegym.thithuchanh.Repository;

import com.codegym.thithuchanh.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByPurchaseDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT o FROM Order o ORDER BY o.quantity * COALESCE(o.product.price, 0) DESC")
    List<Order> findTopOrders(int limit);
}
