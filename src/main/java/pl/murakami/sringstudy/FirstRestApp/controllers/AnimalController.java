package pl.murakami.sringstudy.FirstRestApp.controllers;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import pl.murakami.sringstudy.FirstRestApp.dto.AnimalDTO;
import pl.murakami.sringstudy.FirstRestApp.model.Animal;
import pl.murakami.sringstudy.FirstRestApp.model.utils.AnimalConverting;
import pl.murakami.sringstudy.FirstRestApp.service.AnimalService;
import pl.murakami.sringstudy.FirstRestApp.utils.AnimalErrorResponse;
import pl.murakami.sringstudy.FirstRestApp.utils.AnimalNotCreatedException;
import pl.murakami.sringstudy.FirstRestApp.utils.AnimalNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController // @Controller + @ResponseBody every method
@RequestMapping("/animal")
@RequiredArgsConstructor
public class AnimalController {
    private final AnimalService animalService;

    private final AnimalConverting animalConverting;

    @GetMapping("/{id}")
    public ResponseEntity<AnimalDTO> getOneAnimal(@PathVariable int id) {
        AnimalDTO animalDTO = animalConverting.convertToAnimalDTO(animalService.getOne(id));
        return new ResponseEntity<>(animalDTO, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AnimalDTO>> getAllAnimals() {
        List<AnimalDTO> animalDTOS = animalConverting.convertToAnimalDTOList(animalService.getAll());
        return new ResponseEntity<>(animalDTOS, HttpStatus.OK);
    }

    @PostMapping("/create")
    public HttpStatus create(@RequestBody @Valid AnimalDTO animalDTO){
        try {
            animalService.createAnimal(animalConverting.convertToAnimal(animalDTO));
        }
        catch (ValidationException vle) {throw vle;}
        catch (AnimalNotCreatedException e) {throw e;}
        return HttpStatus.CREATED;
    }

    @PatchMapping("/update")
    public HttpStatus editExistingAnimal(@RequestBody Animal animal) {
        try {
            animalService.changeAnimal(animal);
        } catch (AnimalNotFoundException e) {throw e;}

        return HttpStatus.OK;
    }

    @ExceptionHandler
    public ResponseEntity<AnimalErrorResponse> handleException(AnimalNotFoundException e) {
        AnimalErrorResponse response = new AnimalErrorResponse(
                "Animal was not updated!",
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<AnimalErrorResponse> handleException(AnimalNotCreatedException e) {
        AnimalErrorResponse response = new AnimalErrorResponse(
                "Animal was not created ",
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // 404
    }

    @ExceptionHandler
    private ResponseEntity<AnimalErrorResponse> handleException(ValidationException vle) {
        AnimalErrorResponse response = new AnimalErrorResponse(
                vle.getLocalizedMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // 404
    }
}

