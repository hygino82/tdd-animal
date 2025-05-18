package br.dev.hygino.service;

import br.dev.hygino.dto.RequestAnimalDto;
import br.dev.hygino.dto.ResponseAnimalDto;
import br.dev.hygino.model.Animal;
import br.dev.hygino.repository.AnimalRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final Validator validator;

    public AnimalService(AnimalRepository animalRepository, Validator validator) {
        this.animalRepository = animalRepository;
        this.validator = validator;
    }


    @Transactional(readOnly = true)
    public Page<ResponseAnimalDto> findAllAnimals(Pageable pageable) {
        return animalRepository.findAll(pageable)
                .map(ResponseAnimalDto::new);
    }

    @Transactional(readOnly = true)
    public ResponseAnimalDto findById(Long id) {
        final var res = animalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não existe animal com o Id: " + id));
        return new ResponseAnimalDto(res);
    }

    @Transactional
    public ResponseAnimalDto update(Long id, @Valid RequestAnimalDto dto) {
        var violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        try {
            Animal entity = animalRepository.getReferenceById(id);
            dtoToEntity(dto, entity);
            entity = animalRepository.save(entity);
            return new ResponseAnimalDto(entity);
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Não existe animal com o Id: " + id);
        }
    }

    private void dtoToEntity(RequestAnimalDto dto, Animal entity) {
        entity.setName(dto.name());
        entity.setAge(dto.age());
        entity.setAnimalType(dto.animalType());
    }

    @Transactional
    public ResponseAnimalDto insert(@Valid RequestAnimalDto dto) {
        var violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        Animal entity = new Animal();
        dtoToEntity(dto, entity);
        entity = animalRepository.save(entity);
        return new ResponseAnimalDto(entity);
    }

    public void delete(Long existingId) {
        animalRepository.deleteById(existingId);
    }
}
