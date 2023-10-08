package com.projectalberto.delivery.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientResumeDTO {

    @NotNull
    private Long id;

    @NotBlank
    private String name;
}
