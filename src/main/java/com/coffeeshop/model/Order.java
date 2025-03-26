package com.coffeeshop.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    private static final Logger logger = LoggerFactory.getLogger(Order.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private String orderStatus;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order() {
        logger.info("New Order object created");
    }
}
