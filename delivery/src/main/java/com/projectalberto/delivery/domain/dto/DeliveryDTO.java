package com.projectalberto.delivery.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.projectalberto.delivery.domain.model.DeliveryStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(NON_NULL)
public class DeliveryDTO {

    @JsonProperty(access = READ_ONLY)
    private Long id;

    @Valid
    @NotNull
    private ClientResumeDTO client;

    @Valid
    @NotNull
    private OrderReceiverDTO receiver;

    @NotNull
    private BigDecimal tax;

    @JsonProperty(access = READ_ONLY)
    private DeliveryStatus status;

    @JsonProperty(access = READ_ONLY)
    private OffsetDateTime orderDate;

    @JsonProperty(access = READ_ONLY)
    private OffsetDateTime finishOrderDate;
}
