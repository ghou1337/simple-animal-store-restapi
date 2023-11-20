package pl.murakami.sringstudy.FirstRestApp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.murakami.sringstudy.FirstRestApp.model.Animal;

@Repository
public interface AnimalRepos extends JpaRepository<Animal, Integer> {

}
