import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { TurmasComponent } from './turmas/turmas.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { TurmaDetailComponent } from './turma-detail/turma-detail.component';
import { LoginComponent } from './login/login.component';
import { AuthenticatedGuard } from './authenticated.guard';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent, canActivate: [AuthenticatedGuard] },
  { path: 'detail/:codigo', component: TurmaDetailComponent, canActivate: [AuthenticatedGuard] },
  { path: 'turmas', component: TurmasComponent, canActivate: [AuthenticatedGuard] },
  { path: 'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
