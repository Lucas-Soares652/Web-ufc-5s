import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Turma } from '../turma';
import { TurmaService } from '../turma.service';

@Component({
  selector: 'app-turma-detail',
  templateUrl: './turma-detail.component.html',
  styleUrls: [ './turma-detail.component.css' ]
})
export class TurmaDetailComponent implements OnInit {
  turma: Turma | undefined;

  constructor(
    private route: ActivatedRoute,
    private turmaService: TurmaService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getTurma();
  }

  getTurma(): void {
    const codigo = parseInt(this.route.snapshot.paramMap.get('codigo')!, 10);
    this.turmaService.getTurma(codigo)
      .subscribe(turma => this.turma = turma);
  }

  goBack(): void {
    this.location.back();
  }

  save(codigo: number, disciplina: string, semestre: number): void{
    codigo = codigo
    disciplina = disciplina.trim();
    semestre = semestre;
    if (!codigo || !disciplina || !semestre) { return; }
    this.turmaService.updateTurma({ codigo, disciplina, semestre } as unknown as Turma, this.route.snapshot.paramMap.get('codigo')!)
    .subscribe(() => this.goBack());
  }
}