import { Component, OnInit } from '@angular/core';
import { StorageService } from '../storage.service';
import { Turma } from '../turma';
import { TurmaService } from '../turma.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})
export class DashboardComponent implements OnInit {
  turmas: Turma[] = [];

  constructor(private turmaService: TurmaService, private storage: StorageService) { }

  ngOnInit(): void {
    this.getTurmas();
  }

  getTurmas(): void {
    this.turmaService.getTurmas()
      .subscribe(turmas => this.turmas = turmas.slice(1, 5));
  }

  sair(): void{
    this.storage.clear();
  }
}