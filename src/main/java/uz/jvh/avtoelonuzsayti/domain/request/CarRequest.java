package uz.jvh.avtoelonuzsayti.domain.request;
import lombok.*;
import uz.jvh.avtoelonuzsayti.domain.enums.CarBrand;
import uz.jvh.avtoelonuzsayti.domain.enums.CarStatus;
import uz.jvh.avtoelonuzsayti.domain.enums.Transmission;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CarRequest {
    /// car section
    private String model;
    private CarBrand brand;
    private Transmission transmission;
    private String horsePower;
    private String engineV;
    private LocalDate createdYear;
    private Integer runs;
    private double price;
    private Long ownerId;
    private CarStatus status;
    private String notes;

}
