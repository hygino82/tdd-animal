package br.dev.hygino.dto;

import br.dev.hygino.model.AnimalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestAnimalDto(
        @NotBlank String name,
        @NotNull Integer age,
        @NotNull AnimalType animalType
) {
}
