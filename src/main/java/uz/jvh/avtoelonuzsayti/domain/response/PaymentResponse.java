package uz.jvh.avtoelonuzsayti.domain.response;
import lombok.*;
import uz.jvh.avtoelonuzsayti.domain.entity.Transaction;
import uz.jvh.avtoelonuzsayti.domain.enums.PaymentMethod;
import uz.jvh.avtoelonuzsayti.domain.enums.PaymentStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PaymentResponse {

    private Long id;

    private Transaction transaction;

    private double amount;

    private PaymentMethod paymentMethod;

    private LocalDateTime paymentDate;

    private PaymentStatus status;
}
