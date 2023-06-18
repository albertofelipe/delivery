package com.projectalberto.delivery.api.controller;

import com.projectalberto.delivery.domain.dto.OccurenceInput;
import com.projectalberto.delivery.domain.dto.OccurrenceDTO;
import com.projectalberto.delivery.domain.service.OccurrenceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("deliveries/{deliveryId}/occurrences")
public class OccurrenceController {

    @Autowired
    private OccurrenceService occurrenceService;

    @PostMapping
    @ResponseStatus(CREATED)
    public OccurrenceDTO record (@PathVariable Long deliveryId,
                              @Valid @RequestBody OccurenceInput occurenceInput){

         return occurrenceService
                 .saveOccurrence(deliveryId, occurenceInput.getDescription());
    }

    @GetMapping
    public ResponseEntity<List<OccurrenceDTO>> findAllOccurrences(@PathVariable Long deliveryId){
        return ResponseEntity.ok().body(occurrenceService.findAllOccurrences(deliveryId));
    }

}
