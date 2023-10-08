package com.projectalberto.delivery.domain.service;

import com.projectalberto.delivery.domain.Exceptions.DomainException;
import com.projectalberto.delivery.domain.dto.ClientResumeDTO;
import com.projectalberto.delivery.domain.dto.DeliveryDTO;
import com.projectalberto.delivery.domain.mappers.DeliveryMapper;
import com.projectalberto.delivery.domain.model.Delivery;
import com.projectalberto.delivery.domain.repository.DeliveryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

import static com.projectalberto.delivery.domain.model.DeliveryStatus.*;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private DeliveryMapper deliveryMapper;

    public DeliveryDTO findOneDelivery(Long deliveryId){
        return deliveryRepository.findById(deliveryId)
                .map(deliveryMapper::toDTO)
                .orElseThrow(() -> new DomainException("Delivery not found with id: " + deliveryId));
    }

    public List<DeliveryDTO> findAllDeliveries(){
        return deliveryMapper
                .toCollectionDTO(deliveryRepository.findAll()
        );
    }

    @Transactional
    public DeliveryDTO insertDelivery(DeliveryDTO deliveryDTO){
        ClientResumeDTO clientResumeDTO = clientService
                .findClientToDelivery(deliveryDTO.getClient().getId());

        deliveryDTO.setClient(clientResumeDTO);
        deliveryDTO.setStatus(PENDING);
        deliveryDTO.setOrderDate(OffsetDateTime.now());

        deliveryRepository.save(deliveryMapper.toModel(deliveryDTO));
        return deliveryDTO;
    }

    public void finalizeDelivery(Long deliveryId){
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new EntityNotFoundException("Delivery not found!"));

        delivery.finalizeDelivery();
        deliveryRepository.save(delivery);
    }
}
