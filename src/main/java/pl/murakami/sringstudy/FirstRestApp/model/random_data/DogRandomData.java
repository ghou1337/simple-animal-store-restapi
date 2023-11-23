package pl.murakami.sringstudy.FirstRestApp.model.random_data;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DogRandomData {
    public static final List<String> NAMES = Arrays.asList(
            "Max", "Bella", "Charlie", "Luna", "Rocky", "Daisy", "Buddy", "Coco", "Bailey", "Milo",
            "Rosie", "Zeus", "Ruby", "Oliver", "Sadie", "Toby", "Chloe", "Duke", "Lily", "Teddy",
            "Lucy", "Jack", "Zoey", "Murphy", "Piper", "Winston", "Mia", "Rusty", "Pepper", "Baxter"
    );

    public static final List<String> BREEDS = new ArrayList<>(Arrays.asList(
            "Labrador Retriever", "German Shepherd", "Golden Retriever",
            "French Bulldog", "Beagle", "Poodle", "Bulldog", "Siberian Husky", "Dachshund"
    ));
}
