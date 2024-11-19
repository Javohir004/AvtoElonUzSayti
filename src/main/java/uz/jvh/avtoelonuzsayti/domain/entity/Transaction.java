package uz.jvh.avtoelonuzsayti.domain.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import uz.jvh.avtoelonuzsayti.domain.enums.TransactionStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private User buyer; // Xaridor

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller; // Sotuvchi

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car; // Sotilayotgan mashina

    private double price; // Tranzaksiya narxi

    @Enumerated(EnumType.STRING)
    private TransactionStatus status; // Tranzaksiya holati (masalan, "To'langan", "Bekor qilingan")

    @CreationTimestamp
    private LocalDateTime transactionDate; // Tranzaksiya sanasi
}
