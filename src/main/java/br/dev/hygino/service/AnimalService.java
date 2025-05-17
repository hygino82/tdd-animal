package br.dev.hygino.service;

import br.dev.hygino.dto.ResponseAnimalDto;
import br.dev.hygino.repository.AnimalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Transactional(readOnly = true)
    public List<ResponseAnimalDto> findAllAnimals() {
        return animalRepository.findAll()
                .stream()
                .map(ResponseAnimalDto::new)
                .toList();
    }
}
