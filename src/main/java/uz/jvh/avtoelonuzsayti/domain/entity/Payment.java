package uz.jvh.avtoelonuzsayti.domain.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import uz.jvh.avtoelonuzsayti.domain.enums.PaymentMethod;
import uz.jvh.avtoelonuzsayti.domain.enums.PaymentStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Payment extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    @Column(nullable = false)
    private double amount; // To'lov miqdori

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod; // To'lov usuli (naqd, karta, va h.k.)

    @CreationTimestamp
    private LocalDateTime paymentDate; // To'lov sanasi

    @Enumerated(EnumType.STRING)
    private PaymentStatus status; // To'lov holati
}
