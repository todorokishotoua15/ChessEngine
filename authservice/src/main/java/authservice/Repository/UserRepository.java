package authservice.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import authservice.Model.User;

public interface UserRepository extends JpaRepository<User, String> {

}
