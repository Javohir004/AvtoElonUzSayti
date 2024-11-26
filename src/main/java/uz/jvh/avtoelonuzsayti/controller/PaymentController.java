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
import uz.jvh.avtoelonuzsayti.domain.enums.PaymentStatus;
import uz.jvh.avtoelonuzsayti.domain.enums.TransactionStatus;
import uz.jvh.avtoelonuzsayti.domain.request.PaymentRequest;
import uz.jvh.avtoelonuzsayti.service.CarService;
import uz.jvh.avtoelonuzsayti.service.PaymentService;
import uz.jvh.avtoelonuzsayti.service.TransactionService;

@Controller
@RequestMapping("/api/payment")
@AllArgsConstructor
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CarService carService;

    @Autowired
    private TransactionService transactionService;


    @PostMapping("/create")
    public String create(@RequestParam(name = "carId") Long carId,
                         @RequestParam(name = "transactionId") Long transactionId,
                         @RequestParam(name = "amount") Double amount,
                         @RequestParam(name = "paymentMethod") String paymentMethod,
                         HttpSession session, Model model) {

            Long ownerId = carService.findCarById(carId).getOwnerId();
            Long userId = (Long) session.getAttribute("userId");

        System.out.println(carId);
        System.out.println(ownerId);
        System.out.println(userId);
        try {
            PaymentRequest payment = new PaymentRequest();
            payment.setTransactionId(transactionId);
            payment.setAmount(amount);
            payment.setStatus(PaymentStatus.COMPLETED);
            payment.setPaymentMethod(PaymentMethod.valueOf(paymentMethod));
            paymentService.save(payment);

            carService.sellCar(ownerId, userId , amount);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        transactionService.Complete(transactionId, TransactionStatus.SUCCESS);
        return "/user/user-menu";
    }


    @PostMapping("/cancel")
    public String cancel(@RequestParam(name = "carId") Long carId ,
                          @RequestParam(name = "transactionId") Long transactionId){
        transactionService.Complete(transactionId, TransactionStatus.CANCELLED);
        return "/user/user-menu";
    }

}
