import {Component, OnInit} from '@angular/core';
import {AnimalPage, AnimalResponse} from "../types";
import {AnimalService} from "../animal.service";

@Component({
  selector: 'app-animal-list',
  templateUrl: './animal-list.component.html',
  styleUrls: ['./animal-list.component.css']
})
export class AnimalListComponent implements OnInit {

  constructor(private readonly animalService: AnimalService) {
  }

  animalPage!: AnimalPage;

  ngOnInit(): void {
    this.animalService.findAnimals().subscribe({
      next: (data) => {
        this.animalPage = data;
      },
      error: (err) => {
        console.error('Erro ao buscar animais:', err);
      }
    });
  }


  /*animals: AnimalResponse[] = [
    {
      id: 1,
      name: 'Julieta',
      age: 4,
      animalType: 'CAT'
    },
    {
      id: 2,
      name: 'Rex',
      age: 7,
      animalType: 'DOG'
    },
    {
      id: 3,
      name: 'Mimi',
      age: 2,
      animalType: 'CAT'
    },
    {
      id: 4,
      name: 'Thor',
      age: 5,
      animalType: 'DOG'
    },
    {
      id: 5,
      name: 'Pingo',
      age: 1,
      animalType: 'RABBIT'
    },
    {
      id: 6,
      name: 'Mel',
      age: 3,
      animalType: 'DOG'
    },
    {
      id: 7,
      name: 'Luna',
      age: 2,
      animalType: 'CAT'
    },
    {
      id: 8,
      name: 'Kiko',
      age: 6,
      animalType: 'PARROT'
    },
    {
      id: 9,
      name: 'Simba',
      age: 5,
      animalType: 'CAT'
    },
    {
      id: 10,
      name: 'Bob',
      age: 8,
      animalType: 'DOG'
    }
  ];*/
}
