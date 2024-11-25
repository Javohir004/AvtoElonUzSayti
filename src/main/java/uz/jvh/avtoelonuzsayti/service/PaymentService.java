package uz.jvh.avtoelonuzsayti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jvh.avtoelonuzsayti.domain.entity.Payment;
import uz.jvh.avtoelonuzsayti.domain.entity.Transaction;
import uz.jvh.avtoelonuzsayti.domain.request.PaymentRequest;
import uz.jvh.avtoelonuzsayti.domain.response.PaymentResponse;
import uz.jvh.avtoelonuzsayti.repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;


    @Autowired
    private TransactionService transactionService;

   public PaymentResponse save(PaymentRequest paymentRequest) {
       Payment payment = mapToEntity(paymentRequest);
       Payment save = paymentRepository.save(payment);
       return mapToResponse(save);
   }

   public PaymentResponse update(PaymentRequest paymentRequest , Long paymentId) {
       Payment payment1 = paymentRepository.findById(paymentId)
               .orElseThrow(() -> new RuntimeException("Transaction with this not found"));

       Payment payment = mapToEntity(paymentRequest);
       payment.setId(payment1.getId());
       paymentRepository.save(payment);
       return mapToResponse(payment);
   }

   public PaymentResponse delete(Long paymentId) {
       Payment payment1 = paymentRepository.findById(paymentId)
               .orElseThrow(() -> new RuntimeException("Transaction with this not found"));
       payment1.setActive(false);
       paymentRepository.save(payment1);
       return mapToResponse(payment1);
   }

   public Payment mapToEntity(PaymentRequest paymentRequest) {
       Payment payment = new Payment();
       Long transactionId = paymentRequest.getTransactionId();
       Transaction byId = transactionService.findById(transactionId);
       payment.setPaymentDate(paymentRequest.getPaymentDate());
       payment.setAmount(paymentRequest.getAmount());
       payment.setPaymentMethod(paymentRequest.getPaymentMethod());
       payment.setTransaction(byId);
       payment.setStatus(paymentRequest.getStatus());
       return payment;
   }

   public PaymentResponse mapToResponse(Payment payment) {
    PaymentResponse paymentResponse = new PaymentResponse();
    paymentResponse.setId(payment.getId());
    paymentResponse.setAmount(payment.getAmount());
    paymentResponse.setPaymentDate(payment.getPaymentDate());
    paymentResponse.setPaymentMethod(payment.getPaymentMethod());
    paymentResponse.setTransaction(payment.getTransaction());
    paymentResponse.setStatus(payment.getStatus());
    return paymentResponse;

   }


}
