package uz.jvh.avtoelonuzsayti.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Entity(name = "cars")
public class Car extends BaseEntity {

    @Column(nullable = false)
    private String model;

    @Enumerated(EnumType.STRING)
    private CarBrand brand;

    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    @Column(nullable = false)
    private String horsePower;

    @Column(nullable = false)
    private String engineV;

    private LocalDate createdYear;

    @Column(nullable = false)
    private Integer runs;

    @Column(nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "owner_id") // Mashinaning egasini ko'rsatadi
    private User owner;

    @ElementCollection
    @CollectionTable(name = "car_images", joinColumns = @JoinColumn(name = "car_id"))
    @Column(name = "image_path")
    private List<String> imagePaths = new ArrayList<>();


    @Enumerated(EnumType.STRING)
    private CarStatus status;

    private String notes;

}
