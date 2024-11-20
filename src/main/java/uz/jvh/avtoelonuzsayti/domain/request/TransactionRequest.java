package uz.jvh.avtoelonuzsayti.domain.request;

import lombok.*;
import uz.jvh.avtoelonuzsayti.domain.enums.PaymentMethod;
import uz.jvh.avtoelonuzsayti.domain.enums.TransactionStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionRequest {

    private double amount; // To'lov miqdori

    private LocalDateTime transactionDate; // Tranzaksiya sanasi

    private PaymentMethod paymentMethod; // To'lov usuli (masalan, bank kartasi)

    private TransactionStatus status;

    // Sotilgan mashinaga bog'lash
    private Long carId;


}
