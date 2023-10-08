package com.projectalberto.delivery.api.controller;

import com.projectalberto.delivery.domain.dto.DeliveryDTO;
import com.projectalberto.delivery.domain.service.DeliveryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping("{deliveryId}")
    public ResponseEntity<DeliveryDTO> findOneDelivery(@Valid @PathVariable
                                                           Long deliveryId){
        DeliveryDTO foundDelivery = deliveryService.findOneDelivery(deliveryId);

        return (foundDelivery != null)
                ? ResponseEntity.ok(foundDelivery)
                : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<DeliveryDTO>> findAllDeliveries(){
        return ResponseEntity.ok().body(deliveryService.findAllDeliveries());
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public DeliveryDTO insertOneDelivery(@Valid @RequestBody
                                              DeliveryDTO deliveryDTO){
        return deliveryService.insertDelivery(deliveryDTO);
    }

    @PutMapping("/{deliveryId}/finalization")
    @ResponseStatus(NO_CONTENT)
    public void finalizeDelivery(@PathVariable Long deliveryId){
        deliveryService.finalizeDelivery(deliveryId);
    }

}
