package com.projectalberto.delivery.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
public class OrderReceiver {

    @Column(name = "receiver_name")
    private String name;

    @Column(name = "receiver_street")
    private String street;

    @Column(name = "receiver_number")
    private String number;

    @Column(name = "receiver_neighborhood")
    private String neighborhood;

    @Column(name = "receiver_complement")
    private String complement;

}
