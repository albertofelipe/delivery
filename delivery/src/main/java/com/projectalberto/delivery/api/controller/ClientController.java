package com.projectalberto.delivery.api.controller;

import com.projectalberto.delivery.domain.dto.ClientDTO;
import com.projectalberto.delivery.domain.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

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
    @ResponseStatus(CREATED)
    public ClientDTO insertClient(@Valid @RequestBody
                                   ClientDTO clientDTO){
        return clientService.insertClient(clientDTO);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long clientId,
                                                  @Valid @RequestBody ClientDTO clientDTO){
        ClientDTO clientUpdated = clientService.updateClient(clientId, clientDTO);
        return ResponseEntity.ok().body(clientUpdated);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Object> deleteClient(@PathVariable Long clientId){
        if(!clientService.clientExists(clientId)){
            return ResponseEntity.status(NOT_FOUND).body("Client not found!");
        }
        clientService.deleteClient(clientId);
        return ResponseEntity.noContent().build();
    }
}
