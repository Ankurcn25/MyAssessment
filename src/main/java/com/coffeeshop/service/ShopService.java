package com.coffeeshop.service;

import com.coffeeshop.exception.ShopNotFoundException;

import com.coffeeshop.model.Shop;
import com.coffeeshop.repository.ShopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    private static final Logger logger = LoggerFactory.getLogger(ShopService.class);

    @Autowired
    private ShopRepository shopRepository;

    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public Shop createShop(Shop shop) {
        return shopRepository.save(shop);
    }

    public Shop getShopById(Long id) {
        logger.info("Looking for shop with ID: {}", id);
        return shopRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Shop with ID {} not found", id);
                    return new ShopNotFoundException("Shop not found with ID: " + id);
                });
    }

        public List<Shop> getAllShops() {
            return shopRepository.findAll();
        }

        public Shop addShop(Shop shop){
            logger.info("Saving shop: {}", shop.getName());
            return shopRepository.save(shop);
        }

    public Shop updateShop(Long id, Shop updatedShop) {
        return shopRepository.findById(id).map(shop -> {
            shop.setName(updatedShop.getName());
            shop.setLocation(updatedShop.getLocation());
            logger.info("Updating shop details for ID: {}", id);
            return shopRepository.save(shop);
        }).orElseThrow(() -> {
            logger.error("Shop with ID {} not found for update", id);
            return new ShopNotFoundException("Shop not found with ID: " + id);
        });
    }

}

