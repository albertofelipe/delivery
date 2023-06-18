package com.projectalberto.delivery.domain.service;

import com.projectalberto.delivery.domain.dto.OccurrenceDTO;
import com.projectalberto.delivery.domain.mappers.OccurrenceMapper;
import com.projectalberto.delivery.domain.model.Delivery;
import com.projectalberto.delivery.domain.repository.DeliveryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OccurrenceService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private OccurrenceMapper occurrenceMapper;

    @Transactional
    public OccurrenceDTO saveOccurrence(Long deliveryId, String description){
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new EntityNotFoundException("Delivery not found!"));

        return occurrenceMapper.toDTO(delivery.recordOccurrence(description));
    }

    public List<OccurrenceDTO> findAllOccurrences(Long deliveryId){
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new EntityNotFoundException("Delivery not found!"));

        return occurrenceMapper.toCollectionDTO(delivery.getOccurrences());
    }
}
