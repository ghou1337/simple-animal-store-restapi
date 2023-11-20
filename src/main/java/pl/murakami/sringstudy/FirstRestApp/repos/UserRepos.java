package pl.murakami.sringstudy.FirstRestApp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.murakami.sringstudy.FirstRestApp.model.User;

import java.util.Optional;

@Repository
public interface UserRepos extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

}
