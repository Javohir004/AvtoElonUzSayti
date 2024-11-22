package uz.jvh.avtoelonuzsayti.domain.request;
import lombok.*;
import uz.jvh.avtoelonuzsayti.domain.entity.CarImage;
import uz.jvh.avtoelonuzsayti.domain.entity.User;
import uz.jvh.avtoelonuzsayti.domain.enums.CarBrand;
import uz.jvh.avtoelonuzsayti.domain.enums.CarStatus;
import uz.jvh.avtoelonuzsayti.domain.enums.Transmission;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CarRequest {
    private String model;
    private CarBrand brand = CarBrand.AUDI;
    private Transmission transmission = Transmission.AUTOMATIC;
    private String horsePower;
    private String engineV;
    private LocalDate createdYear;
    private Integer runs;
    private double price;
    private Long ownerId;
    private CarStatus status = CarStatus.AVAILABLE;
    private String notes;
    private List<CarImage> images = new ArrayList<>();
}
