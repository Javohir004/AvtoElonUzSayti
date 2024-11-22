package uz.jvh.avtoelonuzsayti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.jvh.avtoelonuzsayti.domain.entity.Car;

import java.util.List;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByOwnerId(Long ownerId);


}
