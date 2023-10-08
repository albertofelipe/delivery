package com.projectalberto.delivery.domain.common;

import com.projectalberto.delivery.domain.dto.ClientResumeDTO;
import com.projectalberto.delivery.domain.dto.DeliveryDTO;
import com.projectalberto.delivery.domain.dto.OrderReceiverDTO;
import com.projectalberto.delivery.domain.model.Client;
import com.projectalberto.delivery.domain.model.Delivery;
import com.projectalberto.delivery.domain.model.DeliveryStatus;
import com.projectalberto.delivery.domain.model.OrderReceiver;
import static com.projectalberto.delivery.domain.common.ClientConstants.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import static com.projectalberto.delivery.domain.model.DeliveryStatus.PENDING;


public class DeliveryConstants {

    public static final DeliveryDTO DELIVERY_DTO = DeliveryDTO.builder()
            .client(CLIENT_RESUME)
            .receiver(new OrderReceiverDTO(
                    "Alberto",
                    "Dom Pedro",
                    "1234",
                    "Bacuri",
                    "Cerec"
            ))
            .tax(BigDecimal.ONE)
            .status(DeliveryStatus.valueOf(String.valueOf(PENDING)))
            .orderDate(OffsetDateTime.now())
            .finishOrderDate(OffsetDateTime.now())
            .build();

    public static final Delivery DELIVERY = Delivery.builder()
            .id(1L)
            .client(new Client())
            .receiver(new OrderReceiver())
            .tax(BigDecimal.ONE)
            .status(DeliveryStatus.valueOf(String.valueOf(PENDING)))
            .orderDate(OffsetDateTime.now())
            .finishOrderDate(OffsetDateTime.now())
            .build();
}
