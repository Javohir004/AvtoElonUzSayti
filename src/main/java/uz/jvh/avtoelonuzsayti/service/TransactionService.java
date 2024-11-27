package uz.jvh.avtoelonuzsayti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.jvh.avtoelonuzsayti.domain.entity.Car;
import uz.jvh.avtoelonuzsayti.domain.entity.Transaction;
import uz.jvh.avtoelonuzsayti.domain.entity.User;
import uz.jvh.avtoelonuzsayti.domain.enums.TransactionStatus;
import uz.jvh.avtoelonuzsayti.domain.request.TransactionRequest;
import uz.jvh.avtoelonuzsayti.domain.response.TransactionResponse;
import uz.jvh.avtoelonuzsayti.repository.CarRepository;
import uz.jvh.avtoelonuzsayti.repository.TransactionRepository;
import uz.jvh.avtoelonuzsayti.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;


    public TransactionResponse create(TransactionRequest transactionRequest) {
        Transaction transaction = mapToEntity(transactionRequest);
        Transaction save = transactionRepository.save(transaction);
        return mapToResponse(save);
    }

    @Transactional
    public TransactionResponse Complete(Long transactionId, TransactionStatus status) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Setting Status to: " + status);

        transaction.setStatus(status);
        Transaction savedTransaction = transactionRepository.save(transaction);

        return mapToResponse(savedTransaction);
    }


    public List<TransactionResponse> getTransactionsByOwnerId(Long ownerId) {
        List<Transaction> allBySellerIdOrCarOwnerId = transactionRepository.findAllBySeller_IdOrCar_Owner_Id(ownerId, ownerId);
        return allBySellerIdOrCarOwnerId.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
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

        transactionResponse.setSellerName(transaction.getSeller().getUsername());
        transactionResponse.setSellerEmail(transaction.getSeller().getEmail());
        transactionResponse.setSellerPhone(transaction.getSeller().getPhoneNumber());

        transactionResponse.setBuyerName(transaction.getBuyer().getUsername());
        transactionResponse.setBuyerEmail(transaction.getBuyer().getEmail());
        transactionResponse.setBuyerPhone(transaction.getBuyer().getPhoneNumber());
        return transactionResponse;
    }


    public Transaction mapToEntity(TransactionRequest transactionRequest) {

        Car car = carRepository.findById(transactionRequest.getCarId())
                .orElseThrow(() -> new RuntimeException("Car with ID " + transactionRequest.getCarId() + " not found"));

        User seller = userRepository.findById(transactionRequest.getSellerId())
                .orElseThrow(() -> new RuntimeException("User with ID " + transactionRequest.getSellerId() + " not found"));

        User owner = userRepository.findById(transactionRequest.getBuyerId())
                .orElseThrow(()->new RuntimeException("User with ID " + transactionRequest.getBuyerId() + " not found"));

        Transaction transaction = new Transaction();
        transaction.setSeller(seller);
        transaction.setBuyer(owner);
        transaction.setTransactionDate(transactionRequest.getTransactionDate());
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setPaymentMethod(transactionRequest.getPaymentMethod());
        transaction.setTransactionDate(transactionRequest.getTransactionDate());
        transaction.setCar(car);

        return transaction;
    }


}
