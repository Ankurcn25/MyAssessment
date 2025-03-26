package com.coffeeshop.Controller;

import com.coffeeshop.exception.ShopNotFoundException;
import com.coffeeshop.model.Shop;
import com.coffeeshop.repository.ShopRepository;
import com.coffeeshop.service.ShopService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShopServiceTest {

    @Mock
    private ShopRepository shopRepository;

    @InjectMocks
    private ShopService shopService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetShopById_Success() {
        // Arrange
        Shop shop = new Shop();
        when(shopRepository.findById(1L)).thenReturn(Optional.of(shop));

        // Act
        Shop result = shopService.getShopById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Coffee House", result.getName());
        verify(shopRepository, times(1)).findById(1L);
    }

    @Test
    void testGetShopById_NotFound() {
        when(shopRepository.findById(2L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ShopNotFoundException.class, () -> shopService.getShopById(2L));
        assertEquals("Shop with ID 2 not found", exception.getMessage());
    }
}

