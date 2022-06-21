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

  /** GET turmas from the server */
  getTurmas(): Observable<Turma[]> {
    return this.http.get<Turma[]>(this.turmasUrl);
  }

  /** GET hero by id. Return `undefined` when id not found */
  getTurmaNo404<Data>(codigo: number): Observable<Turma> {
    const url = this.turmasUrl;
    return this.http.get<Turma[]>(url)
      .pipe(
        map(turmas => turmas[0]), // returns a {0|1} element array
        tap(h => {
          const outcome = h ? 'fetched' : 'did not find';
          this.log(`${outcome} turma codigo=${codigo}`);
        }),
        catchError(this.handleError<Turma>(`getTurma codigo=${codigo}`))
      );
  }

  /** GET hero by id. Will 404 if id not found */
  getTurma(codigo: number): Observable<Turma> {
    const url = this.turmasUrl;
    return this.http.get<Turma>(`${url}/${codigo}`).pipe(
      tap(_ => this.log(`fetched turma codigo=${codigo}`)),
      catchError(this.handleError<Turma>(`getTurma codigo=${codigo}`))
    );
  }

  /* GET turmas whose name contains search term */
  searchTurmas(term: string): Observable<Turma[]> {
    if (!term.trim()) {
      // if not search term, return empty hero array.
      return of([]);
    }
    return this.http.get<Turma[]>(`${this.turmasUrl}/disciplina?disciplina=${term}`).pipe(
      tap(x => x.length ?
         this.log(`found turmas matching "${term}"`) :
         this.log(`no turmas matching "${term}"`)),
      catchError(this.handleError<Turma[]>('searchTurmas', []))
    );
  }

  //////// Save methods //////////

  /** POST: add a new hero to the server */
  addTurma(turma: Turma): Observable<Turma> {
    return this.http.post<Turma>(this.turmasUrl, turma, this.httpOptions).pipe(
      tap((newTurma: Turma) => this.log(`added turma w/ codigo=${newTurma.codigo}`)),
      catchError(this.handleError<Turma>('addTurma'))
    );
  }

  /** DELETE: delete the hero from the server */
  deleteTurma(codigo: number): Observable<Turma> {
    const url = `${this.turmasUrl}/${codigo}`;

    return this.http.delete<Turma>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted turma codigo=${codigo}`)),
      catchError(this.handleError<Turma>('deleteTurma'))
    );
  }

  /** PUT: update the hero on the server */
  updateTurma(turma: Turma, codigo: string): Observable<any> {
    return this.http.put(`${this.turmasUrl}/${codigo}`, turma, this.httpOptions).pipe(
      tap(_ => this.log(`updated turma codigo=${turma.codigo}`)),
      catchError(this.handleError<any>('updateTurma'))
    );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   *
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  /** Log a HeroService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`TurmaService: ${message}`);
  }
}