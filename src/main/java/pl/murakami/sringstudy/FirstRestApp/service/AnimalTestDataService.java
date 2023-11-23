package pl.murakami.sringstudy.FirstRestApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.murakami.sringstudy.FirstRestApp.model.Animal;
import pl.murakami.sringstudy.FirstRestApp.model.utils.AnimalConverting;
import pl.murakami.sringstudy.FirstRestApp.repos.AnimalRepos;
import pl.murakami.sringstudy.FirstRestApp.respons.MostPopularNameResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnimalTestDataService {

    @Autowired
    private AnimalRepos animalRepos;

    @Autowired
    private AnimalConverting animalConverting;

    public List<String> getNamePopoularity(String name) {
        return animalRepos.getAllByName(name)
                .stream()
                .map(Animal::getName)
                .collect(Collectors.toList());
    }

    public MostPopularNameResponseEntity getMostPopularName() {
        List<Animal> animals = new ArrayList(animalRepos.findAll());
        List<String> names = new ArrayList(animals.stream().map(Animal::getName).toList());

        Map.Entry<String, Long> mostPopularEntry = names.stream()
                .collect(Collectors.groupingBy(name -> name, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);

        // Extract the result
        String mostPopularName = mostPopularEntry != null ? mostPopularEntry.getKey() : null;
        long count = mostPopularEntry != null ? mostPopularEntry.getValue() : 0;

        return new MostPopularNameResponseEntity(mostPopularName, count);
    }
}
