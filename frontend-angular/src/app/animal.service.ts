import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AnimalPage, AnimalRequest} from "./types";

@Injectable({
  providedIn: 'root'
})
export class AnimalService {
  private baseUrl = 'http://localhost:8080/api/v1/animal';

  constructor(private http: HttpClient) {
  }

  insertAnimal(dados: AnimalRequest): Observable<any> {
    return this.http.post<AnimalRequest>(this.baseUrl, dados);
  }

  findAnimals() {
    return this.http.get<AnimalPage>(this.baseUrl);
  }
}
