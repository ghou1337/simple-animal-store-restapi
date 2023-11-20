package pl.murakami.sringstudy.FirstRestApp.model.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.murakami.sringstudy.FirstRestApp.dto.AnimalDTO;
import pl.murakami.sringstudy.FirstRestApp.model.Animal;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AnimalConverting {

    @Autowired
    private ModelMapper modelMapper;

    public Animal convertToAnimal(AnimalDTO animalDTO) {
        return modelMapper.map(animalDTO, Animal.class);
    }

    public AnimalDTO convertToAnimalDTO(Animal animal) {
        return modelMapper.map(animal, AnimalDTO.class);
    }

    public List<AnimalDTO> convertToAnimalDTOList(List<Animal> animals) {
        return animals.stream()
                .map(animal -> convertToAnimalDTO(animal))
                .collect(Collectors.toList());
    }
}
