package uz.jvh.avtoelonuzsayti.domain.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.jvh.avtoelonuzsayti.domain.enums.UserRole;
import uz.jvh.avtoelonuzsayti.domain.enums.UserState;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseEntity{

    @Column(unique = true, nullable = false)
    private String username;

    private String surname;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(unique = true,nullable = false)
    private String email;


    private LocalDate birthDate;

    @Column(nullable = false)
    private String phoneNumber;

    private Double balance;

    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    private UserState state = UserState.ACTIVE;
}
