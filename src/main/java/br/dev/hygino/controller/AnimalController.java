package br.dev.hygino.controller;

import br.dev.hygino.dto.ResponseAnimalDto;
import br.dev.hygino.service.AnimalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
