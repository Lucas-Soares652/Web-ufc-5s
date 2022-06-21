import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { TurmaDetailComponent } from './turma-detail/turma-detail.component';
import { TurmasComponent } from './turmas/turmas.component';
import { TurmaSearchComponent } from './turma-search/turma-search.component';
import { MessagesComponent } from './messages/messages.component';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,

    // The HttpClientInMemoryWebApiModule module intercepts HTTP requests
    // and returns simulated server responses.
    // Remove it when a real server is ready to receive requests.
    //HttpClientInMemoryWebApiModule.forRoot(
    //  InMemoryDataService, { dataEncapsulation: false }
   // )
  ],
  declarations: [
    AppComponent,
    DashboardComponent,
    TurmasComponent,
    TurmaDetailComponent,
    MessagesComponent,
    TurmaSearchComponent
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }