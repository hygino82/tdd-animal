package br.dev.hygino.controller;

import br.dev.hygino.dto.RequestAnimalDto;
import br.dev.hygino.dto.ResponseAnimalDto;
import br.dev.hygino.factory.AnimalFactory;
import br.dev.hygino.model.Animal;
import br.dev.hygino.model.AnimalType;
import br.dev.hygino.service.AnimalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnimalController.class)
public class AnimalControllerTests {
   /* @Autowired
    private MockMvc mockMvc;

    private final String baseUrl = "/api/v1/animal";
    private long existingId, nonExistingId;
    //private RequestAnimalDto insertAnimalDto;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AnimalService animalService;

    @BeforeEach
    public void setUp() {
        //insertAnimalDto = AnimalFactory.createInsertRequestAnimalDto();
        PageImpl<ResponseAnimalDto> page = new PageImpl<>(AnimalFactory.createResponseAnimalDtoList());
        existingId = 1L;
        nonExistingId = 1000L;

        when(animalService.findAllAnimals(any(Pageable.class))).thenReturn(page);

        when(animalService.findById(existingId)).thenReturn(AnimalFactory.createResponseAnimalDto());
        doThrow(IllegalArgumentException.class).when(animalService).findById(nonExistingId);

        when(animalService.update(anyLong(), any(RequestAnimalDto.class))).thenReturn(AnimalFactory.createResponseAnimalDto());

        when(animalService.insert(any(RequestAnimalDto.class))).thenReturn(AnimalFactory.createResponseAnimalDto());
    }

    @Test
    @DisplayName("FindAllPaged deve retornar uma página")
    public void findAllPagedShouldReturnPage() throws Exception {
        final ResultActions result = mockMvc.perform(
                get(baseUrl).accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.content").isNotEmpty());
        result.andExpect(jsonPath("$.content[0].id").value(1L));
        result.andExpect(jsonPath("$.content[0].name").value("Saci"));
    }

    @Test
    @DisplayName("FindById deve retornar um Animal com resposta OK")
    public void findByIdShouldReturnAnimalWhenIdExists() throws Exception {
        final ResultActions result = mockMvc.perform(
                get(baseUrl + "/{id}", existingId)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());

        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.name").exists());
        result.andExpect(jsonPath("$.age").exists());
    }

    @Test
    @DisplayName("FindById deve retornar NOT FOUND")
    public void findByIdShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {
        final ResultActions result = mockMvc.perform(
                get(baseUrl + "/{id}", nonExistingId)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Update deve retornar um animal quando o Id existir")
    public void updateShouldReturnUpdatedAnimalWhenValidId() throws Exception {
        final String jsonBody = objectMapper.writeValueAsString(AnimalFactory.createUpdateRequestAnimalDto());
        final ResultActions result = mockMvc.perform(
                put(baseUrl + "/{id}", existingId)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());

        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.name").exists());
        result.andExpect(jsonPath("$.age").exists());
    }

    @Test
    @DisplayName("Update deve retornar BAD REQUEST quando o Id não existir")
    public void updateShouldReturnBadRequestWhenIdDoesNotExist() throws Exception {
        final String jsonBody = objectMapper.writeValueAsString(AnimalFactory.createUpdateRequestAnimalDto());
        doThrow(IllegalArgumentException.class).when(animalService).update(eq(nonExistingId), any(RequestAnimalDto.class));

        final ResultActions result = mockMvc.perform(
                put(baseUrl + "/{id}", nonExistingId)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Insert deve retornar um Animal quando os dados forem válidos")
    public void insertShouldReturnaAnimalWhenValidData() throws Exception {
        final String jsonBody = objectMapper.writeValueAsString(AnimalFactory.createInsertRequestAnimalDto());
        final ResultActions result = mockMvc.perform(
                post(baseUrl)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }

    @Test
    @DisplayName("Insert deve lançar exceção quando os dados forem inválidos")
    public void insertShouldThrowExceptionWhenInvalidData() throws Exception {
        final RequestAnimalDto invalidDto = new RequestAnimalDto("Juca", null, AnimalType.DOG);

        final String jsonBody = objectMapper.writeValueAsString(invalidDto);
        final ResultActions result = mockMvc.perform(
                post(baseUrl)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());
    }*/
}