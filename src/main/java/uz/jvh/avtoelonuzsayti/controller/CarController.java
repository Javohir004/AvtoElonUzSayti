package uz.jvh.avtoelonuzsayti.controller;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.jvh.avtoelonuzsayti.domain.enums.CarBrand;
import uz.jvh.avtoelonuzsayti.domain.enums.Transmission;
import uz.jvh.avtoelonuzsayti.domain.request.CarRequest;
import uz.jvh.avtoelonuzsayti.domain.response.CarResponse;
import uz.jvh.avtoelonuzsayti.service.CarService;
import uz.jvh.avtoelonuzsayti.service.UserService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/car")
@AllArgsConstructor
public class CarController {

    @Autowired
    private CarService carService;
    @Autowired
    private UserService userService;


    @GetMapping("/create-car" )
    public String addCar(Model model) {
        model.addAttribute("transmission", Transmission.values());
        model.addAttribute("CarBrand", CarBrand.values());
        return "/car/create-car";
    }


    @PostMapping("/create-car")
    public String addCar(@ModelAttribute CarRequest carRequest,
                         @RequestParam("images") MultipartFile[] files,
                         HttpSession session) {
        List<String> imagePaths = new ArrayList<>();
        String uploadDir = "src/main/resources/static/carsImages/";
        for (MultipartFile file : files) {
            try {
                String uniqueFileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path filePath = Paths.get(uploadDir + uniqueFileName);

                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                imagePaths.add("/carsImages/" + uniqueFileName);
            } catch (IOException e) {
                throw new RuntimeException("Faylni yuklashda xatolik: " + e.getMessage());
            }
        }
        Long userId = (Long) session.getAttribute("userId");
        carRequest.setOwnerId(userId);
        carRequest.setImagePaths(imagePaths);
        carService.save(carRequest);

        return "/user/user-menu";
    }


    @GetMapping("/update")
    public String updateCar(@RequestParam(name = "carId") Long carId, Model model) {
        CarResponse carById = carService.findCarById(carId);
        model.addAttribute("CarBrand", CarBrand.values());
        model.addAttribute("Transmission", Transmission.values());
        model.addAttribute("car", carById);
        return "/car/update-car";
    }

    @PostMapping("/update")
    public String updateCar(@RequestParam(name = "carId") Long carId ,
                            CarRequest carRequest,
                            @RequestParam("images") MultipartFile[] files,
                            Model model , HttpSession session) {

        List<String> imagePaths = new ArrayList<>();

        String uploadDir = "src/main/resources/static/carsImages/";
        for (MultipartFile file : files) {
            try {
                String uniqueFileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path filePath = Paths.get(uploadDir + uniqueFileName);

                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                imagePaths.add("/carsImages/" + uniqueFileName);
            } catch (IOException e) {
                throw new RuntimeException("Faylni yuklashda xatolik: " + e.getMessage());
            }
        }
        carRequest.setImagePaths(imagePaths);
        carService.update(carRequest , carId);

        Long userId = (Long) session.getAttribute("userId");
        List<CarResponse> cars = carService.findCarByOwnerID(userId);  // Bitta mashina olish
        model.addAttribute("cars", cars);

        return "/car/show-cars";
    }

    @PostMapping("/delete")
    public String deleteCar(@RequestParam(name = "carId") Long carId, Model model , HttpSession session) {
        carService.delete(carId);

        Long userId = (Long) session.getAttribute("userId");
        List<CarResponse> cars = carService.findCarByOwnerID(userId);  // Bitta mashina olish
        model.addAttribute("cars", cars);

        return "/car/show-cars";
    }



    @GetMapping("/show-car-by-id")
    public String getCarDetails(Model model , HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        List<CarResponse> cars = carService.findCarByOwnerID(userId);  // Bitta mashina olish
        model.addAttribute("cars", cars);// Modelga qo'shish
        return "/car/show-cars"; // car-details.html sahifasini ko'rsatish
    }

    @GetMapping("/get-selling-cars")
    public String getSellingCars(Model model , HttpSession session) {
        List<CarResponse> cars = carService.getAllCars();
        model.addAttribute("cars", cars);
        return "/car/buy-car";
    }

}
