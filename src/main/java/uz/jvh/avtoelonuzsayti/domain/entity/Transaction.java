package uz.jvh.avtoelonuzsayti.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import uz.jvh.avtoelonuzsayti.domain.enums.PaymentMethod;
import uz.jvh.avtoelonuzsayti.domain.enums.TransactionStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction extends BaseEntity {

    @Column(nullable = false)
    private double amount; // To'lov miqdori

    @CreationTimestamp
    private LocalDateTime transactionDate; // Tranzaksiya sanasi

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod; // To'lov usuli (masalan, bank kartasi)

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
}