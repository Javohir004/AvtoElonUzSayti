package uz.jvh.avtoelonuzsayti.domain.request;

import lombok.*;
import uz.jvh.avtoelonuzsayti.domain.enums.UserRole;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserSearchRequest {
    private String username;
    private String email;
    private UserRole role;
}
