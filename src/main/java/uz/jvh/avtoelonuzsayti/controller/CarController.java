package uz.jvh.avtoelonuzsayti.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.jvh.avtoelonuzsayti.domain.entity.Car;
import uz.jvh.avtoelonuzsayti.domain.entity.CarImage;
import uz.jvh.avtoelonuzsayti.domain.entity.Transaction;
import uz.jvh.avtoelonuzsayti.domain.entity.User;
import uz.jvh.avtoelonuzsayti.domain.enums.CarBrand;
import uz.jvh.avtoelonuzsayti.domain.enums.Transmission;
import uz.jvh.avtoelonuzsayti.domain.request.CarRequest;
import uz.jvh.avtoelonuzsayti.domain.response.CarResponse;
import uz.jvh.avtoelonuzsayti.service.CarService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/car")
@AllArgsConstructor
public class CarController {

    @Autowired
    private CarService carService;


    @GetMapping("/create-car")
    public String addCar(Model model) {
        model.addAttribute("transmission", Transmission.values());
        model.addAttribute("CarBrand", CarBrand.values());
        return "/car/create-car";
    }


    @PostMapping("/create-car")
    public String addCar(CarRequest car, @RequestParam("images") MultipartFile[] files) {
        List<CarImage> carImages = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                CarImage carImage = new CarImage();
                carImage.setImageData(file.getBytes()); // Faylni binary ko'rinishda olish
                carImage.setFileName(file.getOriginalFilename()); // Fayl nomi
                carImage.setFileSize(file.getSize()); // Fayl hajmi
                carImage.setFileType(file.getContentType()); // Fayl turi
                carImages.add(carImage);
            } catch (IOException e) {
                throw new RuntimeException("Rasmni saqlashda xatolik: " + e.getMessage());
            }
        }
        car.setOwnerId(4L);  // Bu qismni o'zgartirish kerak bo'lishi mumkin
        car.setImages(carImages);
        carService.save(car);
        return "owner-page";  // Yuborilgan ma'lumotlardan keyin qaytadigan sahifa
    }



    @GetMapping("/show-cars")
    public String showAllCars(Model model) {
        List<CarResponse> cars = carService.getAllCars(); // Servisdan barcha mashinalarni olish
        model.addAttribute("cars", cars);
        return "/car/show-cars";
    }

    @GetMapping("/get-car-by-owner-id")
    public String getCarById(@RequestParam("ownerId") Long ownerId , Model model) {
        List<CarResponse> carByOwnerID = carService.findCarByOwnerID(ownerId);
        model.addAttribute("carByOwnerID", carByOwnerID);
        return "/car/show-cars";
    }

}
