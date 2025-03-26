package com.coffeeshop.Controller;

import com.coffeeshop.model.Shop;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ShopRepositoryTest {

    @Autowired
    private ShopRepository shopRepository;

    @Test
    void testSaveAndFindById() {
        Shop shop = new Shop(null, "Coffee House", "New York", "1234567890", 10);
        Shop savedShop = shopRepository.save(shop);

        Optional<Shop> foundShop = shopRepository.findById(savedShop.getId());
        assertTrue(foundShop.isPresent());
        assertEquals("Coffee House", foundShop.get().getName());
    }
}

