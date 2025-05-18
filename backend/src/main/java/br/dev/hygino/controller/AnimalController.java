package br.dev.hygino.controller;

import br.dev.hygino.dto.RequestAnimalDto;
import br.dev.hygino.dto.ResponseAnimalDto;
import br.dev.hygino.service.AnimalService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/animal")
public class AnimalController {
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public ResponseEntity<Page<ResponseAnimalDto>> findAllAnimals(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(animalService.findAllAnimals(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            ResponseAnimalDto res = animalService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid RequestAnimalDto dto) {
        try {
            ResponseAnimalDto res = animalService.update(id, dto);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Valid RequestAnimalDto dto) {
        try {
            ResponseAnimalDto res = animalService.insert(dto);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
