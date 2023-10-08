package com.projectalberto.delivery.domain.model;

import com.projectalberto.delivery.domain.Exceptions.DomainException;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.projectalberto.delivery.domain.model.DeliveryStatus.*;
import static jakarta.persistence.CascadeType.ALL;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Delivery {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    @Embedded
    private OrderReceiver receiver;

    private BigDecimal tax;

    @OneToMany(mappedBy = "delivery", cascade = ALL)
    private List<Occurrence> occurrences = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    private OffsetDateTime orderDate;

    private OffsetDateTime finishOrderDate;

    public Occurrence recordOccurrence(String description){
        Occurrence occurrence = new Occurrence();
        occurrence.setDescription(description);
        occurrence.setRegistrationDate(OffsetDateTime.now());
        occurrence.setDelivery(this);

        this.getOccurrences().add(occurrence);

        return occurrence;
    }

    public void finalizeDelivery(){
        if(!couldBeFinished()){
            throw new DomainException("This delivery couldn't be finished!");
        }
        setStatus(FINISHED);
        setFinishOrderDate(OffsetDateTime.now());
    }

    public boolean couldBeFinished(){
        return status.equals(PENDING);
    }
}
