package com.coffeeshop.service;

import com.coffeeshop.model.Order;
import com.coffeeshop.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    public Order saveOrder(Order order) {
        try {
            Order savedOrder = orderRepository.save(order);
            logger.info("Order created successfully: {}", savedOrder);
            return savedOrder;
        } catch (Exception e) {
            logger.error("Error creating order: {}", e.getMessage());
            throw new RuntimeException("Could not save the order. Please try again later.");
        }
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Order with ID {} not found", id);
                    return new RuntimeException("Order not found with ID: " + id);
                });
    }

    public List<Order> getAllOrders() {
        try {
            List<Order> orders = orderRepository.findAll();
            logger.info("Retrieved {} orders", orders.size());
            return orders;
        } catch (Exception e) {
            logger.error("Error fetching orders: {}", e.getMessage());
            throw new RuntimeException("Could not retrieve orders. Please try again.");
        }
    }

    public void deleteOrder(Long id) {
        try {
            if (!orderRepository.existsById(id)) {
                logger.warn("Attempted to delete non-existing order with ID {}", id);
                throw new RuntimeException("Order not found with ID: " + id);
            }
            orderRepository.deleteById(id);
            logger.info("Order with ID {} deleted successfully", id);
        } catch (Exception e) {
            logger.error("Error deleting order: {}", e.getMessage());
            throw new RuntimeException("Could not delete order. Please try again.");
        }
    }
}
