package com.projectalberto.delivery.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OccurenceInput {

    @NotBlank
    private String description;
}
