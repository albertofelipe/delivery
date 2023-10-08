package com.projectalberto.delivery.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectalberto.delivery.domain.dto.ClientDTO;
import com.projectalberto.delivery.domain.dto.DeliveryDTO;
import com.projectalberto.delivery.domain.service.ClientService;
import com.projectalberto.delivery.domain.service.DeliveryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.projectalberto.delivery.domain.common.ClientConstants.CLIENT_DTO_wId;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static com.projectalberto.delivery.domain.common.DeliveryConstants.DELIVERY;
import static com.projectalberto.delivery.domain.common.DeliveryConstants.DELIVERY_DTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DeliveryController.class)
class DeliveryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DeliveryService deliveryService;

    @Test
    void findOneDelivery_WithValidId_ReturnsOk() throws Exception {
        when(deliveryService.findOneDelivery(1L)).thenReturn(DELIVERY_DTO);

        mockMvc.perform(get("/deliveries/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(DELIVERY_DTO)));
    }

    @Test
    void findOneDelivery_WithNoExistingId_ReturnsNotFound() throws Exception {
        mockMvc.perform(get("/deliveries/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void insertDelivery_WithValidData_ReturnsCreated() throws Exception {
        when(deliveryService.insertDelivery(any())).thenReturn(DELIVERY_DTO);

        mockMvc.perform(post("/deliveries")
                .content(objectMapper.writeValueAsString(DELIVERY_DTO))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(DELIVERY_DTO)));
    }

    @Test
    void insertDelivery_WithInvalidData_ReturnsBadRequest() throws Exception {
        DeliveryDTO emptyDelivery = new DeliveryDTO();

        mockMvc.perform(post("/deliveries")
                        .content(objectMapper.writeValueAsString(emptyDelivery))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }
}