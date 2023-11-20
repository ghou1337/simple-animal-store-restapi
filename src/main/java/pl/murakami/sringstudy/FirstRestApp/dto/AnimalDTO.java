package pl.murakami.sringstudy.FirstRestApp.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import pl.murakami.sringstudy.FirstRestApp.model.Animal;

public class AnimalDTO {
    @NotEmpty(message = "name shouldn't be empty")
    private String name;

    @NotEmpty(message = "breed shouldn't be empty")
    private String breed;

    @NotNull(message = "age shouldn't be empty")
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public AnimalDTO() {}

    public AnimalDTO(String name, String breed, int age) {
        this.name = name;
        this.breed = breed;
        this.age = age;
    }
}
