package com.coffeeshop.Controller;

import com.coffeeshop.model.Shop;
import com.coffeeshop.service.ShopService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ShopControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ShopService shopService;

    @InjectMocks
    private ShopController shopController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(shopController).build();
    }

    @Test
    void testGetShopById_Success() throws Exception {
        Shop shop = new Shop(1L, "Coffee House", "New York", "1234567890", 10);
        when(shopService.getShopById(1L)).thenReturn(shop);

        mockMvc.perform(get("/shops/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Coffee House"));
    }

    @Test
    void testGetShopById_NotFound() throws Exception {
        when(shopService.getShopById(2L)).thenThrow(new ShopNotFoundException("Shop with ID 2 not found"));

        mockMvc.perform(get("/shops/2"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Shop with ID 2 not found"));
    }
}
