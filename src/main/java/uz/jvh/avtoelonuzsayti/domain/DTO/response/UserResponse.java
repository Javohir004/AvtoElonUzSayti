package uz.jvh.avtoelonuzsayti.domain.DTO.response;

import lombok.*;
import uz.jvh.avtoelonuzsayti.domain.enums.UserRole;

import java.math.BigDecimal;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserResponse {
    private Long id;
    private String username;
    private String surname;
    private UserRole role;
    private String email;
    private LocalDate birthDate;
    private String phoneNumber;
    private BigDecimal balance;
    private String address;
    private Boolean isActive;
}
