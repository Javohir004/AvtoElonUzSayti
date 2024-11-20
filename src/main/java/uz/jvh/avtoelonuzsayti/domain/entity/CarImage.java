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

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car; // Rasmni bog'lash uchun mashina

    @Lob
    @Column(nullable = false)
    private byte[] imageData; // Rasmning binary ma'lumotlari

    @Column(unique = true)
    private String fileName; // Rasm fayl nomi

    private long fileSize; // Fayl hajmi

    private String fileType; // Rasm turi (masalan, JPEG, PNG)
}
