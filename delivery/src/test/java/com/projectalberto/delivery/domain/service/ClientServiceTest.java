package com.projectalberto.delivery.domain.service;

import com.projectalberto.delivery.domain.Exceptions.DomainException;
import com.projectalberto.delivery.domain.dto.ClientDTO;
import com.projectalberto.delivery.domain.mappers.ClientMapper;
import com.projectalberto.delivery.domain.model.Client;
import com.projectalberto.delivery.domain.repository.ClientRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.projectalberto.delivery.domain.common.Constants.*;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @Test
    void findOneClient_WithValidId_ReturnsClient() {

        when(clientMapper.toDTO(CLIENT)).thenReturn(CLIENT_DTO);
        when(clientRepository.findById(anyLong()))
                .thenReturn(Optional.ofNullable(CLIENT));

        ClientDTO sut = clientService.findOneClient(anyLong());

        assertThat(sut).isEqualTo(CLIENT_DTO)
                       .isNotNull();
        verify(clientRepository, times(1)).findById(anyLong());
        verify(clientMapper, times(1)).toDTO(CLIENT);
    }

    @Test
    void findOneClient_WithInvalidId_ThrowsException() {
        doThrow(new DomainException("")).when(clientRepository).findById(anyLong());

        assertThatThrownBy(() -> clientService.findOneClient(anyLong()))
                .isInstanceOf(DomainException.class);

        verify(clientRepository, times(1)).findById(anyLong());
        verifyNoInteractions(clientMapper);
    }

    @Test
    void listClients_ReturnsAllClients() {
        List<Client> expectedClient = singletonList(CLIENT);

        when(clientRepository.findAll()).thenReturn(expectedClient);
        when(clientMapper.toCollectionDTO(expectedClient))
                .thenReturn(singletonList(CLIENT_DTO));

        List<ClientDTO> sut = clientService.findAllClients();

        assertThat(sut)
                .isNotEmpty()
                .hasSize(1)
                .containsExactly(CLIENT_DTO);

        verify(clientRepository, times(1)).findAll();
        verify(clientMapper, times(1))
                .toCollectionDTO(singletonList(CLIENT));
    }

    @Test
    void listClients_WhenNoClientsExists_ReturnsAEmptyList() {
        when(clientRepository.findAll()).thenReturn(emptyList());

        List<ClientDTO> sut = clientService.findAllClients();

        assertThat(sut).isEmpty();
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void insertClient_WithValidData_ReturnsClient() {
        when(clientMapper.toModel(CLIENT_DTO)).thenReturn(CLIENT);
        when(clientMapper.toDTO(CLIENT)).thenReturn(CLIENT_DTO);
        when(clientRepository.save(CLIENT)).thenReturn(CLIENT);

        ClientDTO sut = clientService.insertClient(CLIENT_DTO);

        assertThat(sut).isNotNull()
                       .isEqualTo(CLIENT_DTO);

        verify(clientRepository, times(1)).save(CLIENT);
        verify(clientMapper, times(1)).toDTO(CLIENT);
    }

    @Test
    void insertClient_WithInvalidData_ThrowsException() {
        when(clientMapper.toModel(CLIENT_DTO)).thenReturn(INVALID_CLIENT);
        when(clientRepository.save(INVALID_CLIENT)).thenThrow(DomainException.class);

        assertThatThrownBy(() ->  clientService.insertClient(CLIENT_DTO))
                .isInstanceOf(DomainException.class);

        verify(clientRepository, times(1)).save(INVALID_CLIENT);
    }

    @Test
    void deleteClient_WithValidId_DoesNotThrowAnyException() {
        when(clientRepository.existsById(anyLong())).thenReturn(true);
        assertThatCode(() -> clientService.deleteClient(anyLong())).doesNotThrowAnyException();
    }

    @Test
    void deleteClient_WithInvalidId_ThrowException() {
        when(clientRepository.existsById(anyLong())).thenReturn(true);
        doThrow(new DomainException("")).when(clientRepository).deleteById(anyLong());
        assertThatThrownBy(() -> clientService.deleteClient(anyLong()))
                .isInstanceOf(DomainException.class);
    }

    @Test
    void updateClient_WithValidData_ReturnsClient() {
        when(clientMapper.toDTO(CLIENT)).thenReturn(CLIENT_DTO);
        when(clientMapper.toModel(CLIENT_DTO)).thenReturn(CLIENT);
        when(clientRepository.findById(anyLong()))
                .thenReturn(Optional.ofNullable(CLIENT));
        when(clientRepository.save(any())).thenReturn(CLIENT);

        ClientDTO sut = clientService.updateClient(1L, CLIENT_DTO);

        assertThat(sut).isNotNull();
        assertThat(sut.getId()).isEqualTo(1L);
        verify(clientRepository, times(1)).save(CLIENT);
        verify(clientMapper, times(1)).toDTO(CLIENT);
        verify(clientMapper, times(1)).toModel(CLIENT_DTO);
    }

    @Test
    void updateClient_WithInvalidData_ThrowsException() {
        when(clientRepository.findById(CLIENT_DTO.getId()))
                .thenThrow(new DomainException(""));

        assertThatThrownBy(() -> clientService.updateClient(1L, CLIENT_DTO))
                .isInstanceOf(DomainException.class);
    }

}