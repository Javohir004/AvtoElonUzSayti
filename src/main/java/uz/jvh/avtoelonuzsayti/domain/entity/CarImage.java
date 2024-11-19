package uz.jvh.avtoelonuzsayti.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "car_images")
public class CarImage extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car; // Rasmni bog'lash uchun mashina

    @Lob
    @Column(nullable = false)
    private byte[] imageData; // Rasmning binary ma'lumotlari

    @Column(nullable = false)
    private String fileName; // Rasm fayl nomi

    private long fileSize; // Fayl hajmi

    private String fileType; // Rasm turi (masalan, JPEG, PNG)
}
