package com.coffeeshop.controller;


import com.coffeeshop.model.Order;
import com.coffeeshop.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    // Create Order
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        try {
            Order createdOrder = orderService.saveOrder(order);
            return ResponseEntity.ok(createdOrder);
        } catch (RuntimeException e) {
            logger.error("Error in createOrder API: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
    }

    // Get Order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(orderService.getOrderById(id));
        } catch (RuntimeException e) {
            logger.warn("Error fetching order {}: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    // Delete Order
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.ok("Order deleted successfully");
        } catch (RuntimeException e) {
            logger.warn("Error deleting order {}: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
