package com.projectalberto.delivery.domain.service;

import com.projectalberto.delivery.domain.Exceptions.DomainException;
import com.projectalberto.delivery.domain.dto.ClientDTO;
import com.projectalberto.delivery.domain.dto.ClientResumeDTO;
import com.projectalberto.delivery.domain.mappers.ClientMapper;
import com.projectalberto.delivery.domain.model.Client;
import com.projectalberto.delivery.domain.repository.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .orElseThrow(() -> new DomainException("Client not found with id: " + clientId));
    }

    public List<ClientDTO> findAllClients(){
        return clientMapper
                .toCollectionDTO(clientRepository.findAll());
    }

    @Transactional
    public ClientDTO insertClient(ClientDTO clientDTO){
        if(existsByEmail(clientDTO.getEmail())){
            throw new DomainException("This email is already in use!");
        }
        Client newClient = clientRepository.save(clientMapper.toModel(clientDTO));
        return clientMapper.toDTO(newClient);
    }

    @Transactional
    public void deleteClient(Long clientId){
        if(clientExists(clientId)){
            clientRepository.deleteById(clientId);
        }
    }

    @Transactional
    public ClientDTO updateClient(Long clientId, ClientDTO clientDTO){
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new DomainException("Client not found with id: " + clientId));

        if(!clientDTO.getEmail().equals(client.getEmail())){
            validateEmailUniqueness(clientDTO.getEmail());
        }

        client = clientMapper.toModel(clientDTO);
        client.setId(clientId);

        clientRepository.save(client);
        return clientMapper.toDTO(client);
    }

    private void validateEmailUniqueness(String email) {
        if (existsByEmail(email)) {
            throw new DomainException("This email is already in use!");
        }
    }

    public ClientResumeDTO findClientToDelivery(@Valid Long clientId){
        ClientDTO clientDTO = findOneClient(clientId);
        return new ClientResumeDTO(clientDTO.getId(), clientDTO.getName());
    }

    public boolean existsByEmail(String email){
        return clientRepository.existsByEmail(email);
    }

    public boolean clientExists(Long clientId) {
        return clientRepository.existsById(clientId);
    }
}
