package uz.jvh.avtoelonuzsayti.domain.request;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CarImageRequest {

    private Long carId;

    private byte[] imageData; // Rasmning binary ma'lumotlari

    private String fileName; // Rasm fayl nomi

    private long fileSize; // Fayl hajmi

    private String fileType; // Rasm turi (masalan, JPEG, PNG)
}
