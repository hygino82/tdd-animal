import {Component} from '@angular/core';
import {AnimalService} from "../animal.service";
import {AnimalRequest} from "../types";

@Component({
  selector: 'app-animal-form',
  templateUrl: './animal-form.component.html',
  styleUrls: ['./animal-form.component.css']
})
export class AnimalFormComponent {

  constructor(private readonly service: AnimalService) {
  }

  animals = [
    {label: 'CACHORRO', value: 'DOG'},
    {label: 'GATO', value: 'CAT'},
    {label: 'PAPAGAIO', value: 'PARROT'},
  ];

  selectedAnimal = '';
  nome = '';
  idade = '';

  onSubmit() {
    const data: AnimalRequest = {
      name: this.nome,
      age: Number(this.idade),
      animalType: this.selectedAnimal
    }
    // this.service.insertAnimal(data);

    this.service.insertAnimal(data).subscribe({
      next: (res) => console.log('Resposta:', res),
      error: (err) => console.error('Erro:', err)
    });
    //console.log(data);
  }
}
