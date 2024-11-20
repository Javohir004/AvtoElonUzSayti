package uz.jvh.avtoelonuzsayti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.jvh.avtoelonuzsayti.domain.entity.User;
import uz.jvh.avtoelonuzsayti.domain.enums.UserRole;
import uz.jvh.avtoelonuzsayti.domain.enums.UserState;

import java.util.List;
import java.util.Optional;


@Repository

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

    List<User> findByRoleAndIsActiveTrue(UserRole role);

    Boolean existsByUsernameAndEmail(String username, String email);

    List<User> findUsersByState(UserState state);

    Optional<User> findUsersByUsernameAndEmailAndRole(String username, String email, UserRole role);

}
