package uz.jvh.avtoelonuzsayti.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.jvh.avtoelonuzsayti.domain.enums.PaymentMethod;
import uz.jvh.avtoelonuzsayti.domain.enums.TransactionStatus;
import uz.jvh.avtoelonuzsayti.domain.request.TransactionRequest;
import uz.jvh.avtoelonuzsayti.domain.response.CarResponse;
import uz.jvh.avtoelonuzsayti.domain.response.TransactionResponse;
import uz.jvh.avtoelonuzsayti.service.CarService;
import uz.jvh.avtoelonuzsayti.service.TransactionService;

import java.util.List;

@Controller
@RequestMapping("/api/transaction")
@AllArgsConstructor
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CarService carService;


    @PostMapping("/create")
    public String create(@RequestParam(name = "carId") Long carId ,
                         TransactionRequest transactionRequest,
                         HttpSession session ,
                         Model model) {
        Long userId = (Long) session.getAttribute("userId");
        transactionRequest.setCarId(carId);
        transactionRequest.setSellerId(carService.findCarById(carId).getOwnerId());
        transactionRequest.setBuyerId(userId);
        transactionRequest.setStatus(TransactionStatus.INITIATED);
        TransactionResponse transactionResponse = transactionService.create(transactionRequest);

        model.addAttribute("carId", carId);
        model.addAttribute("transactionId", transactionResponse.getId());
        model.addAttribute("amount", carService.findCarById(carId).getPrice());

        return "payment";
    }


    @GetMapping("/create")
    public String createTransaction(@RequestParam(name = "carId") Long carId, Model model) {
        CarResponse carById = carService.findCarById(carId);
        model.addAttribute("car", carById);
        model.addAttribute("paymentMethod", PaymentMethod.values());
        return "transaction";
    }


    @GetMapping("/transaction-by-ownerId")
    public String transactionByOwnerId(Model model , HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        List<TransactionResponse> transactionsByOwnerId = transactionService.getTransactionsByOwnerId(userId);
        model.addAttribute("transactionsByOwnerId", transactionsByOwnerId);
        return "/user/show-transaction";
    }


}

