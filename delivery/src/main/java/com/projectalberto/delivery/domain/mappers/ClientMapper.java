package com.projectalberto.delivery.domain.mappers;

import com.projectalberto.delivery.domain.dto.ClientDTO;
import com.projectalberto.delivery.domain.model.Client;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ClientMapper {

    private ModelMapper mapper;

    public ClientDTO toDTO(Client client){
        return mapper.map(client, ClientDTO.class);
    }

    public List<ClientDTO> toCollectionDTO(List<Client> entregas){
        return entregas.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
