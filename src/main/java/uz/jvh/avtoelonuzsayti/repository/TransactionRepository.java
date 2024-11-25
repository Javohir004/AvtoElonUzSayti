package uz.jvh.avtoelonuzsayti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.jvh.avtoelonuzsayti.domain.entity.Transaction;

import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**Bu sotgan odam **/
    List<Transaction> findAllBySeller_Id(Long sellerId);


    /** Bu sotib olgan odam **/
    List<Transaction> findAllByCar_Owner_Id(Long ownerId);
}
