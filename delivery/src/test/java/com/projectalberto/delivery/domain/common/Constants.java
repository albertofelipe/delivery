package com.projectalberto.delivery.domain.common;

import com.projectalberto.delivery.domain.dto.ClientDTO;
import com.projectalberto.delivery.domain.model.Client;

public class Constants {

    public static final ClientDTO CLIENT_DTO = ClientDTO.builder()
            .id(1L)
            .name("Alberto")
            .email("felipe@gmail.com")
            .phone("99999999")
            .build();

    public static final Client CLIENT= Client.builder()
            .id(1L)
            .name("Alberto")
            .email("felipe@gmail.com")
            .phone("99999999")
            .build();

    public static final Client INVALID_CLIENT = Client.builder()
            .id(1L)
            .name("")
            .email("felipe@gmailcom")
            .phone("99999999")
            .build();
}
