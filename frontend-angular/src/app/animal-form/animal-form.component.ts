import {Component} from '@angular/core';

@Component({
  selector: 'app-animal-form',
  templateUrl: './animal-form.component.html',
  styleUrls: ['./animal-form.component.css']
})
export class AnimalFormComponent {
  animals = [
    {label: 'CACHORRO', value: 'DOG'},
    {label: 'GATO', value: 'CAT'},
    {label: 'PAPAGAIO', value: 'PARROT'},
  ];

  selectedAnimal = '';
  nome = '';
  baseUrl = 'http://localhost:8080/api/v1/animal';

  onSubmit() {
    console.log('Nome:', this.nome);
    console.log('Animal selecionado:', this.selectedAnimal);
  }
}
