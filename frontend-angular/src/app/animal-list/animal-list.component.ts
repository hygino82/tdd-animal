import { Component, OnInit } from '@angular/core';
import { AnimalPage } from "../types";
import { AnimalService } from "../animal.service";

@Component({
  selector: 'app-animal-list',
  templateUrl: './animal-list.component.html',
  styleUrls: ['./animal-list.component.css']
})
export class AnimalListComponent implements OnInit {
  animalPage!: AnimalPage;

  constructor(private readonly animalService: AnimalService) {}

  removerAnimal(id: number) {
    this.animalService.removeAnimal(id).subscribe({
      next: () => {
        console.log('Animal removido com sucesso');
        this.carregarLista(); // Atualiza a lista após a remoção
      },
      error: (err) => console.error('Erro ao remover animal', err)
    });
  }

  carregarLista() {
    this.animalService.findAnimals().subscribe({
      next: (data) => {
        this.animalPage = data;
      },
      error: (err) => {
        console.error('Erro ao buscar animais:', err);
      }
    });
  }

  ngOnInit(): void {
    this.carregarLista();
  }
}
