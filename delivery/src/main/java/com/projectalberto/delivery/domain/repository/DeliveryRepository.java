package com.projectalberto.delivery.domain.repository;

import com.projectalberto.delivery.domain.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
