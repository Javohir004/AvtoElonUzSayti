package uz.jvh.avtoelonuzsayti.domain.request;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import uz.jvh.avtoelonuzsayti.domain.entity.Transaction;
import uz.jvh.avtoelonuzsayti.domain.enums.PaymentMethod;
import uz.jvh.avtoelonuzsayti.domain.enums.PaymentStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PaymentRequest {

    private Long transactionId;


    private double amount; // To'lov miqdori

    private PaymentMethod paymentMethod; // To'lov usuli (naqd, karta, va h.k.)


    private LocalDateTime paymentDate; // To'lov sanasi

    private PaymentStatus status; // To'lov holati


}
