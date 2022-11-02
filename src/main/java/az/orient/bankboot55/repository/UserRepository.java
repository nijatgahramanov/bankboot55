package az.orient.bankboot55.repository;

import az.orient.bankboot55.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsernameAndPasswordAndActive(String username, String password, Integer active);

}
