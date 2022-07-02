import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpResponse
} from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { StorageService } from './storage.service';

@Injectable()
export class HttpBasicAuthInterceptor implements HttpInterceptor {

  constructor(private storage: StorageService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log('HttpBasicAuthInterceptor - Test');
    const credentials = this.storage.get('authorization');
   
    if(credentials){
      console.log('HttpBasicAuthInterceptor - credentials: ' + credentials);
      return next.handle(request.clone({setHeaders:{Authorization: 'Basic ' + credentials}})).pipe(
      tap(event => {
        if (event instanceof HttpResponse) {
          console.log("entrou");
           if(event.status.valueOf() == 401){
            this.storage.clear();
            console.log("logout automático - Test");
          }
        }
      })
    );
    }
    else return next.handle(request).pipe(
      tap(event => {
        if (event instanceof HttpResponse) {
          console.log("entrou");
           if(event.status.valueOf() == 401){
            this.storage.clear();
            console.log("logout automático - Test");
          }
        }
      })
    );;
  }
}