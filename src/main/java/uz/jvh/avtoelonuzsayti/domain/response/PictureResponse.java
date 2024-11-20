package uz.jvh.avtoelonuzsayti.domain.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PictureResponse {

    private Long carId;
    private byte[] imageData;
    private String fileName;
    private long fileSize;
    private String fileType;

}
