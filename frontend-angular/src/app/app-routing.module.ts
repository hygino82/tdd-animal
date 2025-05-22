import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AnimalFormComponent} from "./animal-form/animal-form.component";
import {AnimalListComponent} from "./animal-list/animal-list.component";

const routes: Routes = [
  {
    path: 'cadastrar',
    component: AnimalFormComponent
  },
  {
    path: '',
    component: AnimalListComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
