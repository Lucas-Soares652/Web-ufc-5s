import { Component, OnInit } from '@angular/core';

import { Turma } from '../turma';
import { TurmaService } from '../turma.service';

@Component({
  selector: 'app-turmas',
  templateUrl: './turmas.component.html',
  styleUrls: ['./turmas.component.css']
})
export class TurmasComponent implements OnInit {
  turmas: Turma[] = [];

  constructor(private turmaService: TurmaService) { }

  ngOnInit(): void {
    this.getTurmas();
  }

  getTurmas(): void {
    this.turmaService.getTurmas()
    .subscribe(turmas => this.turmas = turmas);
  }

  add(codigo: string, disciplina: string, semestre: string): void {
    codigo = codigo
    disciplina = disciplina.trim();
    semestre = semestre;
    if (!codigo || !disciplina || !semestre) { return; }
    this.turmaService.addTurma({ codigo, disciplina, semestre } as unknown as Turma)
      .subscribe(turma => {
        this.turmas.push(turma);
      });
  }

  delete(turma: Turma): void {
    this.turmas = this.turmas.filter(h => h !== turma);
    this.turmaService.deleteTurma(turma.codigo).subscribe();
  }
}