package br.dev.hygino.factory;

import br.dev.hygino.dto.RequestAnimalDto;
import br.dev.hygino.dto.ResponseAnimalDto;
import br.dev.hygino.model.Animal;
import br.dev.hygino.model.AnimalType;

import java.util.Arrays;
import java.util.List;

public class AnimalFactory {
    public static List<Animal> createAnimalList() {
        return Arrays.asList(
                new Animal(1L, "Saci", 4, AnimalType.CAT),
                new Animal(2L, "Bender", 1, AnimalType.DOG),
                new Animal(3L, "Rico", 30, AnimalType.PARROT),
                new Animal(4L, "Ozzy", 1, AnimalType.CAT)
        );
    }

    public static Animal CreateAnimal() {
        return new Animal(1L, "Saci Updated", 4, AnimalType.CAT);
    }

    public static RequestAnimalDto createUpdateRequestAnimalDto() {
        return new RequestAnimalDto("Updated Saci", 4, AnimalType.CAT);
    }

    public static RequestAnimalDto createInsertRequestAnimalDto() {
        return new RequestAnimalDto("Saci", 4, AnimalType.CAT);
    }

    public static List<ResponseAnimalDto> createResponseAnimalDtoList() {
        return Arrays.asList(
                new ResponseAnimalDto(1L, "Saci", 4, AnimalType.CAT),
                new ResponseAnimalDto(2L, "Bender", 1, AnimalType.DOG),
                new ResponseAnimalDto(3L, "Rico", 30, AnimalType.PARROT),
                new ResponseAnimalDto(4L, "Ozzy", 1, AnimalType.CAT)
        );
    }

    public static ResponseAnimalDto createResponseAnimalDto() {
        return new ResponseAnimalDto(1L, "Saci", 4, AnimalType.CAT);
    }
}
