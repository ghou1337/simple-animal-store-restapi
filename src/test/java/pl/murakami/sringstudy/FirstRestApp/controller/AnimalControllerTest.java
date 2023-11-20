package pl.murakami.sringstudy.FirstRestApp.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.murakami.sringstudy.FirstRestApp.controllers.AnimalController;
import pl.murakami.sringstudy.FirstRestApp.dto.AnimalDTO;
import pl.murakami.sringstudy.FirstRestApp.model.Animal;
import pl.murakami.sringstudy.FirstRestApp.model.utils.AnimalConverting;
import pl.murakami.sringstudy.FirstRestApp.repos.AnimalRepos;
import pl.murakami.sringstudy.FirstRestApp.security.JwtFilter;
import pl.murakami.sringstudy.FirstRestApp.service.AnimalService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AnimalController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class AnimalControllerTest {
    @MockBean private AnimalService animalService;

    @MockBean private AnimalConverting animalConverting;

    @MockBean private JwtFilter jwtFilter;

    @MockBean private AnimalRepos animalRepos;

    @MockBean private ModelMapper modelMapper;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void init() throws Exception {
        Animal animal = new Animal(0, "rocky", "buldog", 1);
        AnimalDTO animalDTO = new AnimalDTO("rocky", "buldog", 1);

        Mockito.when(animalService.getOne(0)).thenReturn(animal);
        Mockito.when(animalConverting.convertToAnimalDTO(animal)).thenReturn(animalDTO);
    }
    @Test
    void testGetOneAnimal() throws Exception {
        mockMvc.perform(get("/animal/{id}", 0))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name").value("rocky"))
                .andExpect(jsonPath("$.breed").value("buldog"))
                .andExpect(jsonPath("$.age").value(1))
                .andReturn();
        verify(animalService, times(1)).getOne(0);
    }

    @Test
    void testCreatingNewAnimal() throws Exception {
        AnimalDTO animalDTO = new AnimalDTO("rocky", "buldog", 1);
        String animalDTOJson = objectMapper.writeValueAsString(animalDTO);
        mockMvc.perform(post("/animal/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(animalDTOJson))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetAllAnimals() throws Exception {
        List<AnimalDTO> mockAnimalsDTO = Arrays.asList(new AnimalDTO("rocky", "buldog", 5), new AnimalDTO("rocky", "buldog", 5));
        Mockito.when(animalConverting.convertToAnimalDTOList(animalService.getAll())).thenReturn(mockAnimalsDTO);

        String res = mockMvc.perform(get("/animal/all"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println("Response: " + res);
    }
}
