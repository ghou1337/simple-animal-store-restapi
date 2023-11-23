package pl.murakami.sringstudy.FirstRestApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.murakami.sringstudy.FirstRestApp.model.Animal;
import pl.murakami.sringstudy.FirstRestApp.repos.AnimalRepos;
import pl.murakami.sringstudy.FirstRestApp.utils.AnimalNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepos animalRepos;

    @Autowired
    private UserService userService;

    public Boolean findOne(int animalId) {
        return animalRepos.findById(animalId).isPresent();
    }

    public Animal getOne(int id) {
        Optional<Animal> animal = animalRepos.findById(id);
        return animal.orElseThrow(AnimalNotFoundException::new);
    }

    public List<Animal> getAll() {
        return animalRepos.findAll();
    }
    @Transactional
    public void createAnimal(Animal animal) {
        enrichAnimal(animal);
        animalRepos.save(animal);
    }

    private void enrichAnimal(Animal animal) {
        animal.setCreatedAt(LocalDateTime.now());
        animal.setUpdatedAt(LocalDateTime.now());
        animal.setCreatedWho("ADMIN");
    }

    public void changeAnimal(Animal animal) {
        Animal oldAnimal;
        if (findOne(animal.getId())  == false){
            throw new AnimalNotFoundException();
        } else {
             oldAnimal = getOne(animal.getId());
        }
        animal.setCreatedAt(oldAnimal.getCreatedAt());
        animal.setUpdatedAt(LocalDateTime.now());
        animal.setCreatedWho(userService.getAuthenticatedUser().getAuthorities());
        animalRepos.save(animal);
    }

    public Integer getAmountOfAnimals() {
        return animalRepos.findAll().size();
    }
}
