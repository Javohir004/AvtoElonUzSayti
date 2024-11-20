package uz.jvh.avtoelonuzsayti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.jvh.avtoelonuzsayti.domain.entity.Transaction;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
