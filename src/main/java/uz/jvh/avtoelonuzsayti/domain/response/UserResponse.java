package uz.jvh.avtoelonuzsayti.domain.response;

import lombok.*;
import uz.jvh.avtoelonuzsayti.domain.enums.UserRole;
import uz.jvh.avtoelonuzsayti.domain.enums.UserState;

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
    private Double balance;
    private String address;
    private UserState userState;
    private String password;
}
