package com.projectalberto.delivery.api.controller;

import com.projectalberto.delivery.domain.dto.ClientDTO;
import com.projectalberto.delivery.domain.model.Client;
import com.projectalberto.delivery.domain.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientDTO> findOneClient(@Valid @PathVariable
                                                       Long clientId){
        ClientDTO clientDTO = clientService.findOneClient(clientId);

        return ResponseEntity.ok().body(clientDTO);
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAllClients(){
        return ResponseEntity.ok().body(clientService.findAllClients());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO insertClient(@Valid @RequestBody
                                   ClientDTO clientDTO){
        return clientService.insertClient(clientDTO);
    }
}
