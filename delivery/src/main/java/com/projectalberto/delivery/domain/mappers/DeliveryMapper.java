package com.projectalberto.delivery.domain.mappers;

import com.projectalberto.delivery.domain.dto.DeliveryDTO;
import com.projectalberto.delivery.domain.model.Delivery;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class DeliveryMapper {

    private ModelMapper mapper;

    public DeliveryDTO toDTO(Delivery delivery){
        return mapper.map(delivery, DeliveryDTO.class);
    }

    public List<DeliveryDTO> toCollectionDTO(List<Delivery> deliveries){
        return deliveries.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Delivery toModel(DeliveryDTO deliveryDTO){
        return mapper.map(deliveryDTO, Delivery.class);
    }
}
