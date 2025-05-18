package br.dev.hygino;

import br.dev.hygino.dto.RequestAnimalDto;
import br.dev.hygino.dto.ResponseAnimalDto;
import br.dev.hygino.factory.AnimalFactory;
import br.dev.hygino.model.Animal;
import br.dev.hygino.model.AnimalType;
import br.dev.hygino.repository.AnimalRepository;
import br.dev.hygino.service.AnimalService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
public class AnimalServiceTest {
    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private AnimalService animalService;

    private long existingId;
    private long nonExistingId;
    private Pageable pageable;
    //private RequestAnimalDto insertAnimal;
    private RequestAnimalDto updateAnimal;


    @BeforeEach
    public void setUp() throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        factory.close();

        animalService = new AnimalService(animalRepository, validator);

        existingId = 1L;
        nonExistingId = 1000L;
        pageable = PageRequest.of(0, 10);
        //insertAnimal = AnimalFactory.createInsertRequestAnimalDto();
        updateAnimal = AnimalFactory.createUpdateRequestAnimalDto();
        final PageImpl<Animal> page = new PageImpl<>(AnimalFactory.createAnimalList());

        //se não fazer esse mock vai retornar null
        when(animalRepository.save(any(Animal.class))).thenReturn(AnimalFactory.CreateAnimal());

        when(animalRepository.findAll(pageable)).thenReturn(page);

        when(animalRepository.findById(existingId)).thenReturn(Optional.of(AnimalFactory.CreateAnimal()));
        when(animalRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        when(animalRepository.getReferenceById(existingId)).thenReturn(AnimalFactory.CreateAnimal());
        when(animalRepository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("Deve retornar uma página de ResponseAnimalDto")
    public void findAllShouldReturnResponseAnimalDtoPage() {
        final var result = animalService.findAllAnimals(pageable);
        assertFalse(result.isEmpty());
        assertEquals(4, result.getTotalElements());
        assertEquals("Saci", result.getContent().get(0).name());
        assertEquals("Bender", result.getContent().get(1).name());
        assertEquals("Rico", result.getContent().get(2).name());
        assertEquals("Ozzy", result.getContent().get(3).name());
    }

    @Test
    @DisplayName("findById deve retornar um ResponseAnimalDto quando o Id for válido")
    public void findByIdShouldReturnResponseAnimalDtoWhenValidId() {
        final ResponseAnimalDto result = animalService.findById(existingId);
        assertEquals(1L, result.id());
        assertEquals("Saci Updated", result.name());
        assertEquals(4, result.age());
        assertEquals(AnimalType.CAT, result.animalType());
    }

    @Test
    @DisplayName("findById deve lançar IllegalArgumentException quando o Id não existir")
    public void findByIdShouldThrowIllegalArgumentExceptionWhenInvalid() {
        final IllegalArgumentException res = assertThrows(IllegalArgumentException.class, () -> animalService.findById(nonExistingId));
        assertEquals("Não existe animal com o Id: " + nonExistingId, res.getMessage());
    }

    @Test
    @DisplayName("Update deve retornar ResponseAnimalDto quando o id for válido")
    public void updateShouldReturnResponseAnimalDtoWhenValidId() {
        when(animalRepository.save(any())).thenReturn(AnimalFactory.CreateAnimal());

        assertNotNull(updateAnimal);
        final var result = animalService.update(existingId, updateAnimal);
        assertNotNull(result);
        assertEquals("Saci Updated", result.name());
        assertEquals(4, result.age());
    }

    @Test
    @DisplayName("Update dele lançar IllegalArgumentException quando o Id não existir")
    public void updateShouldThrowIllegalArgumentExceptionWhenInvalidId() {
        final IllegalArgumentException res = assertThrows(IllegalArgumentException.class, () -> animalService.update(nonExistingId, updateAnimal));
        assertEquals("Não existe animal com o Id: " + nonExistingId, res.getMessage());
    }

    @Test
    @DisplayName("Insert deve retornar um ResponseAnimalDto quando os dados forem válidos")
    public void insertShouldReturnResponseAnimalDtoWhenValidData() {
        final var result = animalService.insert(updateAnimal);
        assertNotNull(result.id());
        assertEquals("Saci Updated", result.name());
        assertEquals(4, result.age());
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
    public void deleteShouldDoNothingWhenIdExists() {
        assertDoesNotThrow(() -> animalService.delete(existingId));

        verify(animalRepository, times(1)).deleteById(existingId);
    }
}
