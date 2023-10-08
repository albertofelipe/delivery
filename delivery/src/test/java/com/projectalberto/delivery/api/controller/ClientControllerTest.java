package com.projectalberto.delivery.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectalberto.delivery.domain.Exceptions.DomainException;
import com.projectalberto.delivery.domain.dto.ClientDTO;
import com.projectalberto.delivery.domain.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.projectalberto.delivery.domain.common.ClientConstants.CLIENT_DTO;
import static com.projectalberto.delivery.domain.common.ClientConstants.CLIENT_DTO_wId;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClientService clientService;

    @Test
    void findOneClient_WithValidId_ReturnsOk() throws Exception {
        when(clientService.findOneClient(1L)).thenReturn(CLIENT_DTO);

        mockMvc.perform(get("/clients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(CLIENT_DTO));
    }

    @Test
    void findOneClient_WithNoExistingId_ReturnsNotFound() throws Exception {
        mockMvc.perform(get("/planets/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void insertClient_WithValidData_ReturnsCreated() throws Exception {
        when(clientService.insertClient(CLIENT_DTO_wId)).thenReturn(CLIENT_DTO_wId);

        mockMvc.perform(post("/clients")
                .content(objectMapper.writeValueAsString(CLIENT_DTO_wId))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value(CLIENT_DTO_wId));
    }

    @Test
    void insertClient_WithInvalidData_ReturnsBadRequest() throws Exception {
        ClientDTO emptyClient = new ClientDTO();
        ClientDTO invalidClient = ClientDTO.builder().name("").email("").phone("").build();

        mockMvc.perform(post("/clients")
                .content(objectMapper.writeValueAsString(emptyClient))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        mockMvc.perform(post("/clients")
                .content(objectMapper.writeValueAsString(invalidClient))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateClient_WithValidData_ReturnsOk() throws Exception {
        Long clientId = 1L;
        when(clientService.updateClient(clientId, CLIENT_DTO_wId)).thenReturn(CLIENT_DTO);
        when(clientService.clientExists(clientId)).thenReturn(true);

        mockMvc.perform(put("/clients/{clientId}", clientId)
                .content(objectMapper.writeValueAsString(CLIENT_DTO))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(CLIENT_DTO));
    }

    @Test
    void updateClient_WithInvalidId_ReturnsBadRequest() throws Exception {
        Long clientId = 1L;
        when(clientService.updateClient(clientId, CLIENT_DTO))
                .thenThrow(new DomainException("Client not found with id: " + clientId));

        mockMvc.perform(put("/clients/{clientId}", clientId)
                .content(objectMapper.writeValueAsString(CLIENT_DTO))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    void updateClient_WithInvalidEmail_ReturnsBadRequest() throws Exception {
        Long clientId = 1L;
        when(clientService.existsByEmail(CLIENT_DTO.getEmail())).thenReturn(true);
        when(clientService.updateClient(clientId, CLIENT_DTO))
                .thenThrow(new DomainException("This email is already in use!"));

        mockMvc.perform(put("/clients/{clientId}", clientId)
                .content(objectMapper.writeValueAsString(CLIENT_DTO))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    void deleteClient_WithValidId_ReturnsNoContent() throws Exception {
        Long clientId = 1L;
        when(clientService.clientExists(clientId)).thenReturn(true);
        mockMvc.perform(delete("/clients/" + 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteClient_WithInvalidId_ReturnsNotFound() throws Exception {
        Long clientId = 1L;
        doThrow(new DomainException("")).when(clientService).deleteClient(clientId);

        mockMvc.perform(delete("/clients/{clientId}", clientId))
                .andExpect(status().isNotFound());
    }
}