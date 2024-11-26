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

    private double amount;

    private LocalDateTime transactionDate;

    private PaymentMethod paymentMethod;

    private TransactionStatus status = TransactionStatus.INITIATED;

    private Long carId;

    private Long sellerId;

    private Long buyerId;


}
