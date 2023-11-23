package pl.murakami.sringstudy.FirstRestApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.murakami.sringstudy.FirstRestApp.dto.AnimalDTO;
import pl.murakami.sringstudy.FirstRestApp.model.random_data.DogRandomData;
import pl.murakami.sringstudy.FirstRestApp.model.utils.AnimalConverting;
import pl.murakami.sringstudy.FirstRestApp.respons.MostPopularNameResponseEntity;
import pl.murakami.sringstudy.FirstRestApp.service.AnimalService;
import pl.murakami.sringstudy.FirstRestApp.service.AnimalTestDataService;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/animal")
public class AnimalTestDataController {

    private final AnimalTestDataService animalTestDataService;

    private final AnimalService animalService;

    private final AnimalConverting animalConverting;

    @GetMapping("/create-100-dogs")
    public HttpStatus createAnimals() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            String breed = DogRandomData.BREEDS.get(random.nextInt(8));
            String name = DogRandomData.NAMES.get(random.nextInt(30));
            animalService.createAnimal(animalConverting.convertToAnimal(new AnimalDTO(name, breed, random.nextInt(15))));
        }
        return HttpStatus.CREATED;
    }

    @GetMapping("/get-most-name-popularity")
    public String namePopularity(@RequestParam String name) {
        int amountOfNames = animalTestDataService.getNamePopoularity(name).size();
        return "This name was used: " + amountOfNames + " times.";
    }

    @GetMapping("/get-most-popular-name")
    private ResponseEntity<MostPopularNameResponseEntity> getMostPopularName() {
        return new ResponseEntity<>(animalTestDataService.getMostPopularName(), HttpStatus.OK);
    }
}
