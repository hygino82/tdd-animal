package br.dev.hygino;

import br.dev.hygino.model.Animal;
import br.dev.hygino.model.AnimalType;
import br.dev.hygino.repository.AnimalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("dev")
public class AnimalRepositoryTests {

    @Autowired
    private AnimalRepository animalRepository;
    private long validId;
    private long invalidId;

    @BeforeEach
    public void setUp() {
        validId = 1L;
        invalidId = 1000L;
    }

    @Test
    @DisplayName("Deve retornar o id gerado automáticamente")
    public void whenInsertAnimalShouldReturnNotNullId() {
        final Animal insertAnimal = new Animal("Ozzy", 1, AnimalType.CAT);
        final Animal result = animalRepository.save(insertAnimal);
        assertNotNull(result.getId());
    }

    @Test
    @DisplayName("findAll deve retornar uma lista vazia")
    public void findAllShouldReturnEmptyList() {
        animalRepository.deleteAll();
        final List<Animal> result = animalRepository.findAll();
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("findAll deve retornar uma lista com onze elementos")
    public void findAllShouldReturnListWithElevenElements() {
        final List<Animal> result = animalRepository.findAll();
        assertEquals(11, result.size());
    }

    @Test
    @DisplayName("findById deve retornar um optional não vazio")
    public void findByIdShouldReturnNotEmptyOptional() {
        final var result = animalRepository.findById(validId);
        assertTrue(result.isPresent());
    }

    @Test
    @DisplayName("findById deve retornar um optional vazio")
    public void findByIdShouldReturnEmptyOptional() {
        final var result = animalRepository.findById(invalidId);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Deve reduzir o tamanho da lista ao deletar um elemento")
    public void deleteShouldReduceListSize() {
        animalRepository.deleteById(validId);
        final List<Animal> result = animalRepository.findAll();
        assertEquals(10, result.size());
    }
}
