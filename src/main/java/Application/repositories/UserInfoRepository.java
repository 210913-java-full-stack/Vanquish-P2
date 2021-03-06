package Application.repositories;

import Application.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {
    Optional<UserInfo> findByUsernameAndPassword(String username, String password);
    Optional<UserInfo> findByUsername(String username);
    Optional<UserInfo> findByFirstName(String firstName);
}
