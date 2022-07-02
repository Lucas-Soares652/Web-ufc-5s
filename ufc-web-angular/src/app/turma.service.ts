import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Turma } from './turma';
import { MessageService } from './message.service';


@Injectable({ providedIn: 'root' })
export class TurmaService {

  private turmasUrl = 'http://localhost:8080/api/turma';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  getTurmas(): Observable<Turma[]> {
    return this.http.get<Turma[]>(this.turmasUrl);
  }

  getTurmaNo404<Data>(codigo: number): Observable<Turma> {
    const url = this.turmasUrl;
    return this.http.get<Turma[]>(url)
      .pipe(
        map(turmas => turmas[0]),
        tap(h => {
          const outcome = h ? 'fetched' : 'did not find';
          this.log(`${outcome} turma codigo=${codigo}`);
        }),
        catchError(this.handleError<Turma>(`getTurma codigo=${codigo}`))
      );
  }

  getTurma(codigo: number): Observable<Turma> {
    const url = this.turmasUrl;
    return this.http.get<Turma>(`${url}/${codigo}`).pipe(
      tap(_ => this.log(`fetched turma codigo=${codigo}`)),
      catchError(this.handleError<Turma>(`getTurma codigo=${codigo}`))
    );
  }

  searchTurmas(term: string): Observable<Turma[]> {
    if (!term.trim()) {
      return of([]);
    }
    return this.http.get<Turma[]>(`${this.turmasUrl}/disciplina?disciplina=${term}`).pipe(
      tap(x => x.length ?
         this.log(`found turmas matching "${term}"`) :
         this.log(`no turmas matching "${term}"`)),
      catchError(this.handleError<Turma[]>('searchTurmas', []))
    );
  }

  addTurma(turma: Turma): Observable<Turma> {
    return this.http.post<Turma>(this.turmasUrl, turma, this.httpOptions).pipe(
      tap((newTurma: Turma) => this.log(`added turma w/ codigo=${newTurma.codigo}`)),
      catchError(this.handleError<Turma>('addTurma'))
    );
  }

  deleteTurma(codigo: number): Observable<Turma> {
    const url = `${this.turmasUrl}/${codigo}`;

    return this.http.delete<Turma>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted turma codigo=${codigo}`)),
      catchError(this.handleError<Turma>('deleteTurma'))
    );
  }

  updateTurma(turma: Turma, codigo: string): Observable<any> {
    return this.http.put(`${this.turmasUrl}/${codigo}`, turma, this.httpOptions).pipe(
      tap(_ => this.log(`updated turma codigo=${turma.codigo}`)),
      catchError(this.handleError<any>('updateTurma'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

  private log(message: string) {
    this.messageService.add(`TurmaService: ${message}`);
  }
}