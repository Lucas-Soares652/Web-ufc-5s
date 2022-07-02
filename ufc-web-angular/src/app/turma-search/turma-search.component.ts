import { Component, OnInit } from '@angular/core';

import { Observable, Subject } from 'rxjs';

import {
   debounceTime, distinctUntilChanged, switchMap
 } from 'rxjs/operators';

import { Turma } from '../turma';
import { TurmaService } from '../turma.service';

@Component({
  selector: 'app-turma-search',
  templateUrl: './turma-search.component.html',
  styleUrls: [ './turma-search.component.css' ]
})
export class TurmaSearchComponent implements OnInit {
  turmas$!: Observable<Turma[]>;
  private searchTerms = new Subject<string>();

  constructor(private turmaService: TurmaService) {}

  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
    this.turmas$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.turmaService.searchTurmas(term)),
    );
  }
}