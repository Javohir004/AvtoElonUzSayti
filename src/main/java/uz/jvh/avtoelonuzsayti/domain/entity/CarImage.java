package uz.jvh.avtoelonuzsayti.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "car_images")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarImage extends BaseEntity {


    @Lob
    @Column(nullable = false)
    private byte[] imageData; // Rasmning binary ma'lumotlari

    @Column(unique = true)
    private String fileName; // Rasm fayl nomi

    private long fileSize; // Fayl hajmi

    private String fileType; // Rasm turi (masalan, JPEG, PNG)
}
