package az.orient.bankboot55.repository;

import az.orient.bankboot55.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAllByActive(Integer active);

    Customer findByIdAndActive(Long id, Integer active);
}
