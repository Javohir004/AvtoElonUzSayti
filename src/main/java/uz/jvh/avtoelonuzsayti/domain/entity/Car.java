package uz.jvh.avtoelonuzsayti.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import uz.jvh.avtoelonuzsayti.domain.enums.CarBrand;
import uz.jvh.avtoelonuzsayti.domain.enums.CarStatus;
import uz.jvh.avtoelonuzsayti.domain.enums.Transmission;

import java.time.LocalDate;


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

    @CreationTimestamp
    private LocalDate createdYear;

    @Column(nullable = false)
    private Integer runs;

    @Column(nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false) // Mashinaning egasini ko'rsatadi
    private User owner;

    @Enumerated(EnumType.STRING)
    private CarStatus status;

    private String notes;

}
