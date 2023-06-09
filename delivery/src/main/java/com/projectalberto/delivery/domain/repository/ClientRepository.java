package com.projectalberto.delivery.domain.repository;

import com.projectalberto.delivery.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
