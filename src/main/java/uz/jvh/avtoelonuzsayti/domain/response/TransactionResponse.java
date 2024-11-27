package uz.jvh.avtoelonuzsayti.domain.response;
import lombok.*;
import uz.jvh.avtoelonuzsayti.domain.entity.Car;
import uz.jvh.avtoelonuzsayti.domain.enums.PaymentMethod;
import uz.jvh.avtoelonuzsayti.domain.enums.TransactionStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionResponse {

    private Long id;

    private double amount;

    private LocalDateTime transactionDate;

    private PaymentMethod paymentMethod;

    private TransactionStatus status;

    private Car car;

    private String sellerName;
    private String sellerEmail;
    private String sellerPhone;

    private String buyerName;
    private String buyerEmail;
    private String buyerPhone;

}
