import { Component, OnInit } from '@angular/core';
import { Turma } from '../turma';
import { TurmaService } from '../turma.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})
export class DashboardComponent implements OnInit {
  turmas: Turma[] = [];

  constructor(private heroService: TurmaService) { }

  ngOnInit(): void {
    this.getTurmas();
  }

  getTurmas(): void {
    this.heroService.getTurmas()
      .subscribe(turmas => this.turmas = turmas.slice(1, 5));
  }
}