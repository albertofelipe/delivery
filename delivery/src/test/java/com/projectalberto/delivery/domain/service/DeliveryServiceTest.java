package com.projectalberto.delivery.domain.service;

import com.projectalberto.delivery.domain.Exceptions.DomainException;
import com.projectalberto.delivery.domain.dto.ClientResumeDTO;
import com.projectalberto.delivery.domain.dto.DeliveryDTO;
import com.projectalberto.delivery.domain.mappers.DeliveryMapper;
import com.projectalberto.delivery.domain.model.Delivery;
import com.projectalberto.delivery.domain.repository.DeliveryRepository;
import jakarta.validation.Valid;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.projectalberto.delivery.domain.common.ClientConstants.*;
import static com.projectalberto.delivery.domain.common.DeliveryConstants.DELIVERY;
import static com.projectalberto.delivery.domain.common.DeliveryConstants.DELIVERY_DTO;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeliveryServiceTest {

    @Mock
    private DeliveryMapper deliveryMapper;

    @Mock
    private DeliveryRepository deliveryRepository;

    @Mock
    private ClientService clientService;

    @InjectMocks
    private DeliveryService deliveryService;

    @Test
    void findOneDelivery_WithValidId_ReturnsDelivery() {
        when(deliveryRepository.findById(anyLong()))
                .thenReturn(Optional.ofNullable(DELIVERY));
        when(deliveryMapper.toDTO(DELIVERY)).thenReturn(DELIVERY_DTO);

        DeliveryDTO sut = deliveryService.findOneDelivery(anyLong());

        assertThat(sut).isEqualTo(DELIVERY_DTO)
                .isNotNull();
        verify(deliveryRepository, times(1)).findById(anyLong());
        verify(deliveryMapper, times(1)).toDTO(DELIVERY);
    }

    @Test
    void findOneDelivery_WithInvalidId_ThrowsException() {
        doThrow(new DomainException("")).when(deliveryRepository).findById(anyLong());

        assertThatThrownBy(() -> deliveryService.findOneDelivery(anyLong()))
                .isInstanceOf(DomainException.class);

        verify(deliveryRepository, times(1)).findById(anyLong());
        verifyNoInteractions(deliveryMapper);
    }

    @Test
    void findAllDeliveries_ReturnsAllDeliveries() {
        List<Delivery> expectedDelivery = singletonList(DELIVERY);

        when(deliveryRepository.findAll()).thenReturn(expectedDelivery);
        when(deliveryMapper.toCollectionDTO(expectedDelivery))
                .thenReturn(singletonList(DELIVERY_DTO));

        List<DeliveryDTO> sut = deliveryService.findAllDeliveries();

        assertThat(sut)
                .isNotEmpty()
                .hasSize(1)
                .containsExactly(DELIVERY_DTO);

        verify(deliveryRepository, times(1)).findAll();
        verify(deliveryMapper, times(1))
                .toCollectionDTO(singletonList(DELIVERY));
    }

    @Test
    void listDeliveries_WhenNoDeliveriesExists_ReturnsAEmptyList() {
        when(deliveryRepository.findAll()).thenReturn(emptyList());

        List<DeliveryDTO> sut = deliveryService.findAllDeliveries();

        assertThat(sut).isEmpty();
        verify(deliveryRepository, times(1)).findAll();
    }

    @Test
    void insertDelivery_WithValidData_ReturnsDelivery() {
        Long clientId = 1L;
        when(clientService.findClientToDelivery(clientId))
                .thenReturn(CLIENT_RESUME);
        when(deliveryRepository.save(DELIVERY)).thenReturn(DELIVERY);
        when(deliveryMapper.toModel(DELIVERY_DTO)).thenReturn(DELIVERY);

        DeliveryDTO sut = deliveryService.insertDelivery(DELIVERY_DTO);

        assertThat(sut).isNotNull()
                .isEqualTo(DELIVERY_DTO);
    }

    @Test
    void insertDelivery_WithInvalidData_ThrowsException() {
        when(clientService.findClientToDelivery(1L)).thenReturn(CLIENT_RESUME);
        when(deliveryMapper.toModel(DELIVERY_DTO)).thenReturn(DELIVERY);
        when(deliveryRepository.save(DELIVERY)).thenThrow(DomainException.class);

        assertThatThrownBy(() ->  deliveryService.insertDelivery(DELIVERY_DTO))
                .isInstanceOf(DomainException.class);

        verify(deliveryRepository, times(1)).save(DELIVERY);
    }

}