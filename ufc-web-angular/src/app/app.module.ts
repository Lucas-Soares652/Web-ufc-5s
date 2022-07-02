import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { TurmaDetailComponent } from './turma-detail/turma-detail.component';
import { TurmasComponent } from './turmas/turmas.component';
import { TurmaSearchComponent } from './turma-search/turma-search.component';
import { MessagesComponent } from './messages/messages.component';
import { LoginComponent } from './login/login.component';
import { HttpBasicAuthInterceptor } from './http-basic-auth.interceptor';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  declarations: [
    AppComponent,
    DashboardComponent,
    TurmasComponent,
    TurmaDetailComponent,
    MessagesComponent,
    TurmaSearchComponent,
    LoginComponent
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: HttpBasicAuthInterceptor, multi: true}],
  bootstrap: [ AppComponent ]
})
export class AppModule { }