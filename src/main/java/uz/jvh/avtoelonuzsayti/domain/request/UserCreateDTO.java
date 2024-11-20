package uz.jvh.avtoelonuzsayti.domain.request;
import lombok.*;
import uz.jvh.avtoelonuzsayti.domain.enums.UserRole;


import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserCreateDTO {
    private String username;
    private String surname;
    private String password;
    private UserRole role;
    private String email;
    private LocalDate birthDate;
    private String phoneNumber;
    private BigDecimal balance;
    private String address;
    private String verificationToken;

}