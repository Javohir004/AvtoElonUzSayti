package uz.jvh.avtoelonuzsayti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.jvh.avtoelonuzsayti.domain.entity.CarImage;


@Repository
public interface CarImageRepository extends JpaRepository<CarImage, Long> {
}
