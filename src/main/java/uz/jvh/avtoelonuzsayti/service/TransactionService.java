package uz.jvh.avtoelonuzsayti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.jvh.avtoelonuzsayti.domain.entity.Car;
import uz.jvh.avtoelonuzsayti.domain.entity.Transaction;
import uz.jvh.avtoelonuzsayti.domain.request.TransactionRequest;
import uz.jvh.avtoelonuzsayti.domain.response.TransactionResponse;
import uz.jvh.avtoelonuzsayti.repository.CarRepository;
import uz.jvh.avtoelonuzsayti.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CarRepository carRepository;

    public TransactionResponse create(TransactionRequest transactionRequest) {
        Transaction transaction = mapToEntity(transactionRequest);
        Transaction save = transactionRepository.save(transaction);
        return mapToResponse(save);
    }

    public TransactionResponse update(TransactionRequest transactionRequest, Long id) {
        Transaction transaction = transactionRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Transaction with ID not found"));

        Transaction transaction1 = mapToEntity(transactionRequest);
        transaction1.setId(transaction1.getId());
        Transaction save = transactionRepository.save(transaction1);
        return mapToResponse(save);
    }

    public TransactionResponse delete(Long id) {
        Transaction transaction = transactionRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Transaction with ID not found"));
        transaction.setActive(false);
        transactionRepository.save(transaction);
        return mapToResponse(transaction);
    }

    public Transaction findById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction with ID " + id + " not found"));
    }

    public TransactionResponse mapToResponse(Transaction transaction) {
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setCar(transaction.getCar());
        transactionResponse.setId(transaction.getId());
        transactionResponse.setStatus(transaction.getStatus());
        transactionResponse.setAmount(transaction.getAmount());
        transactionResponse.setTransactionDate(transaction.getTransactionDate());
        transactionResponse.setPaymentMethod(transaction.getPaymentMethod());
        return transactionResponse;
    }



    public Transaction mapToEntity(TransactionRequest transactionRequest) {
        Car car = carRepository.findById(transactionRequest.getCarId())
                .orElseThrow(() -> new RuntimeException("Car with ID " + transactionRequest.getCarId() + " not found"));
        Transaction transaction = new Transaction();
        transaction.setTransactionDate(transactionRequest.getTransactionDate());
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setPaymentMethod(transactionRequest.getPaymentMethod());
        transaction.setTransactionDate(transactionRequest.getTransactionDate());
        transaction.setCar(car);
        return transaction;
    }


}
