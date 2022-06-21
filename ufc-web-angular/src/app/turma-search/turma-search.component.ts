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

  // Push a search term into the observable stream.
  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
    this.turmas$ = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      // switch to new search observable each time the term changes
      switchMap((term: string) => this.turmaService.searchTurmas(term)),
    );
  }
}