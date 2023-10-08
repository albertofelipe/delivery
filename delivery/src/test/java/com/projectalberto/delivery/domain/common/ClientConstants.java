package com.projectalberto.delivery.domain.common;

import com.projectalberto.delivery.domain.dto.ClientDTO;
import com.projectalberto.delivery.domain.dto.ClientResumeDTO;
import com.projectalberto.delivery.domain.model.Client;

public class ClientConstants {

    public static final ClientDTO CLIENT_DTO = ClientDTO.builder()
            .id(1L)
            .name("Alberto")
            .email("felipe@gmail.com")
            .phone("99999999")
            .build();

    public static final ClientDTO CLIENT_DTO_wId = ClientDTO.builder()
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

    public static final ClientResumeDTO CLIENT_RESUME = ClientResumeDTO.builder()
            .id(1L)
            .name("Alberto")
            .build();
}
