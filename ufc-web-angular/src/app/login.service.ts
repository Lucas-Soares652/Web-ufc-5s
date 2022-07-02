import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private API_USER: string = 'http://localhost:8080/api/user';

  constructor(private http: HttpClient) { }

  login(username: string, password: string){
    const headers = new HttpHeaders(
      {
        authorization : 'Basic ' + btoa(username + ':' + password)
      }
    );
    return this.http.get<Object>(this.API_USER, {headers: headers});
  }
}
