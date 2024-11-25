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

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false) // Sotilgan mashinaga bog'lash
    private Car car;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false) // Sotuvchi
    private User seller;


}
