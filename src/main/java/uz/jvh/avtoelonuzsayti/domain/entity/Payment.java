package uz.jvh.avtoelonuzsayti.domain.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import uz.jvh.avtoelonuzsayti.domain.enums.PamentStatus;
import uz.jvh.avtoelonuzsayti.domain.enums.PaymentMethod;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction; // To'lovga tegishli tranzaksiya

    @Column(nullable = false)
    private double amount; // To'lov miqdori

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod; // To'lov usuli (naqd, karta, va h.k.)

    @CreationTimestamp
    private LocalDateTime paymentDate; // To'lov sanasi

    @Enumerated(EnumType.STRING)
    private PamentStatus status; // To'lov holati
}
