package uz.jvh.avtoelonuzsayti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.jvh.avtoelonuzsayti.domain.entity.Payment;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {


}
