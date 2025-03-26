package com.coffeeshop.controller;


import com.coffeeshop.model.Shop;
import com.coffeeshop.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shops")
public class ShopController {

    private static final Logger logger = LoggerFactory.getLogger(ShopController.class);

    @Autowired
    private ShopService shopService;

    @PostMapping
    public ResponseEntity<Shop> createShop(@RequestBody Shop shop) {
        return ResponseEntity.ok(shopService.createShop(shop));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shop> getShop(@PathVariable Long id) {
        logger.info("Fetching shop with ID: {}", id);
        return ResponseEntity.ok(shopService.getShopById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Shop>> getAllShops() {
        logger.info("Fetching all shops...");
        List<Shop> shops = shopService.getAllShops();
        logger.info("Fetched {} shops", shops.size());
        return ResponseEntity.ok(shops);
    }

    @PostMapping("/add")
    public ResponseEntity<Shop> addShop(@RequestBody Shop shop) {
        logger.info("Adding new shop: {}", shop.getName());
        Shop createdShop = shopService.addShop(shop);
        logger.info("Shop added with ID: {}", createdShop.getId());
        return ResponseEntity.ok(createdShop);
    }

    @PutMapping("/shop/update/{id}")
    public ResponseEntity<Shop> updateShop(@PathVariable Long id, @RequestBody Shop updatedShop) {
        logger.info("Updating shop with ID: {}", id);
        Shop shop = shopService.updateShop(id, updatedShop);
        return ResponseEntity.ok(shop);
    }
}
