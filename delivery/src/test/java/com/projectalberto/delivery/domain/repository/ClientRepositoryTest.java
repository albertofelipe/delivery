package com.projectalberto.delivery.domain.repository;

import com.projectalberto.delivery.domain.model.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @AfterEach
    void tearDown() {
        clientRepository.deleteAll();
    }

    @Test
    void ItShouldCheckWhenClientExistsByEmail() {

        //given
        var client = Client.builder()
                .name("Alberto")
                .email("alberto@gmail.com")
                .phone("9999999").build();

        clientRepository.save(client);

        //when
        boolean expected = clientRepository.existsByEmail(client.getEmail());

        //then
        assertThat(expected).isTrue();
    }

    @Test
    void ItShouldCheckWhenClientDoesNotExistsByEmail() {

        //given
        String email = "alberto@gmail.com";

        //when
        boolean expected = clientRepository.existsByEmail(email);

        //then
        assertThat(expected).isFalse();
    }

    @Test
    void ItShouldCheckWhenClientExistsById() {

        //given
        var client = Client.builder()
                .name("Alberto")
                .email("alberto@gmail.com")
                .phone("9999999").build();

        clientRepository.save(client);

        //when
        boolean expected = clientRepository.existsById(client.getId());

        //then
        assertThat(expected).isTrue();
    }

    @Test
    void ItShouldCheckWhenClientDoesNotExistsById() {

        //given
        Long id = 1L;

        //when
        boolean expected = clientRepository.existsById(id);

        //then
        assertThat(expected).isFalse();
    }
}