package com.projectalberto.delivery.api.controller;

import com.projectalberto.delivery.domain.dto.DeliveryDTO;
import com.projectalberto.delivery.domain.service.DeliveryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryRequest;

    @GetMapping("{deliveryId}")
    public ResponseEntity<DeliveryDTO> findOneDelivery(@Valid @PathVariable
                                                           Long deliveryId){
        DeliveryDTO foundDelivery = deliveryRequest.findOneDelivery(deliveryId);

        return ResponseEntity.ok().body(foundDelivery);
    }

    @GetMapping
    public ResponseEntity<List<DeliveryDTO>> findAllDeliveries(){
        return ResponseEntity.ok().body(deliveryRequest.findAllDeliveries());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryDTO insertOneDeliverie(@Valid @RequestBody
                                              DeliveryDTO deliveryDTO){
        return deliveryRequest.insertDelivery(deliveryDTO);
    }

}
