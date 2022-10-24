package az.orient.bankboot55.repository;

import az.orient.bankboot55.entity.Account;
import az.orient.bankboot55.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    List<Account> findAllByActive(Integer active);

    List<Account> findAllByCustomerAndActive(Customer customer,Integer active);

    Account findAccountByIdAndActive(Long id,Integer active);

}
