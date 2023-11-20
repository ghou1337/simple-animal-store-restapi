package pl.murakami.sringstudy.FirstRestApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.murakami.sringstudy.FirstRestApp.model.Animal;
import pl.murakami.sringstudy.FirstRestApp.repos.AnimalRepos;
import pl.murakami.sringstudy.FirstRestApp.service.AnimalService;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AnimalServiceTest {

    @InjectMocks
    private AnimalService animalService;

    @Mock
    private AnimalRepos animalRepos;

    @Test
    void getAllAnimalsTest() {
        Animal animal1 = new Animal(0, "rocky", "buldog", 5);
        Animal animal2 = new Animal(0, "kiki", "labrador", 12);
        Mockito.when(animalRepos.findAll()).thenReturn(List.of(animal1, animal2));

        int amountOfAnimals = animalService.getAmountOfAnimals();
        Assertions.assertEquals(2, amountOfAnimals);

    }
}
