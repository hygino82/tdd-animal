package br.dev.hygino.service;

import br.dev.hygino.dto.RequestAnimalDto;
import br.dev.hygino.dto.ResponseAnimalDto;
import br.dev.hygino.factory.AnimalFactory;
import br.dev.hygino.model.AnimalType;
import br.dev.hygino.repository.AnimalRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class AnimalServiceTestsIT {
    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private AnimalService animalService;

    @Autowired
    private Validator validator;

    private int countTotalAnimals;
    private long existingId, invalidId;

    @BeforeEach
    public void setUp() {
        countTotalAnimals = 11;
        existingId = 1L;
        invalidId = 10000L;
    }

    @Test
    @DisplayName("FindAllAnimals deve retornar uma página com 11 elementos")
    public void findAllAnimalsShouldReturnPage() {
        final PageRequest pageable = PageRequest.of(0, 10);
        final Page<ResponseAnimalDto> result = animalService.findAllAnimals(pageable);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(0, result.getNumber());
        assertEquals(10, result.getSize());
        assertEquals(countTotalAnimals, result.getTotalElements());
        assertEquals("Morgana", result.getContent().get(0).name());
        assertEquals("Rex", result.getContent().get(1).name());
    }

    @Test
    @DisplayName("Insert deve retornar um ResponseAnimalDto quando os dados forem válidos")
    public void insertShouldReturnResponseAnimalDtoWhenValidData() {
        final RequestAnimalDto insertDto = new RequestAnimalDto("Frodo", 12, AnimalType.DOG);
        final var result = animalService.insert(insertDto);
        assertNotNull(result);
        assertNotNull(result.id());
        assertEquals("Frodo", result.name());
        assertEquals(12, result.age());
        assertEquals(AnimalType.DOG, result.animalType());
    }

    @Test
    @DisplayName("Insert deve lançar ConstraintViolationException quando idade for nula")
    void insertShouldThrowConstraintViolationExceptionWhenAgeIsNull() {
        RequestAnimalDto dto = new RequestAnimalDto("Juca", null, AnimalType.CAT);

        ConstraintViolationException exception = assertThrows(
                ConstraintViolationException.class,
                () -> animalService.insert(dto)
        );

        boolean ageViolationFound = exception.getConstraintViolations().stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("age"));

        assertTrue(ageViolationFound, "Esperava violação no campo 'age'");
    }

    @Test
    @DisplayName("findById deve lançar IllegalArgumentException quando o Id não existir")
    public void findByIdShouldThrowIllegalArgumentExceptionWhenInvalid() {
        final IllegalArgumentException res = assertThrows(IllegalArgumentException.class, () -> animalService.findById(invalidId));
        assertEquals("Não existe animal com o Id: " + invalidId, res.getMessage());
    }

    @Test
    @DisplayName("findById deve retornar um ResponseAnimalDto quando o Id for válido")
    public void findByIdShouldReturnResponseAnimalDtoWhenValidId() {
        final ResponseAnimalDto result = animalService.findById(existingId);
        assertEquals(1L, result.id());
        assertEquals("Morgana", result.name());
        assertEquals(2, result.age());
        assertEquals(AnimalType.DOG, result.animalType());
    }

    @Test
    @DisplayName("Update deve retornar ResponseAnimalDto quando o id for válido")
    public void updateShouldReturnResponseAnimalDtoWhenValidId() {
        final RequestAnimalDto updateAnimal = AnimalFactory.createUpdateRequestAnimalDto();
        final ResponseAnimalDto result = animalService.update(existingId, updateAnimal);
        assertNotNull(result);
        assertEquals("Updated Saci", result.name());
        assertEquals(4, result.age());
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() {
        assertDoesNotThrow(() -> animalService.delete(existingId));
    }
}
