package com.projectalberto.delivery.domain.service;

import com.projectalberto.delivery.domain.Exceptions.DomainException;
import com.projectalberto.delivery.domain.dto.ClientDTO;
import com.projectalberto.delivery.domain.mappers.ClientMapper;
import com.projectalberto.delivery.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    public ClientDTO findOneClient (Long clientId){
        return clientRepository.findById(clientId)
                .map(clientMapper::toDTO)
                .orElseThrow(() -> new DomainException("Client not found!"));
    }

    public List<ClientDTO> findAllClients(){
        return clientMapper
                .toCollectionDTO(clientRepository.findAll());
    }
}
