import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { TurmasComponent } from './turmas/turmas.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { TurmaDetailComponent } from './turma-detail/turma-detail.component';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'detail/:codigo', component: TurmaDetailComponent },
  { path: 'turmas', component: TurmasComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
