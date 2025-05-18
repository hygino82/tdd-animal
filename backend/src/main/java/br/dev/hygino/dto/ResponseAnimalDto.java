package br.dev.hygino.dto;

import br.dev.hygino.model.Animal;
import br.dev.hygino.model.AnimalType;

public record ResponseAnimalDto(
        Long id,
        String name,
        Integer age,
        AnimalType animalType
) {

    public ResponseAnimalDto(Animal animal) {
        this(animal.getId(), animal.getName(), animal.getAge(), animal.getAnimalType());
    }
}
