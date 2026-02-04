import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root',
})
export class ApiClient {
  baseUrl: string = "" //baseURL is blank for now thanks to the proxy to make http work. We'll have to do something with this later.
  httpClient: HttpClient

  constructor(httpClient: HttpClient) {
    this.httpClient = httpClient
  }

  // TODO: investigate why Observable<User> lacks HttpResponse wrapping
  register(username: string, password: string, email: string): Observable<User> {
    let user: User = {username: username, password: password, email: email}
    return this.httpClient.post<User>(this.baseUrl+"/register", {observe: 'response'})
  }

  login(username: string, password: string): Observable<HttpResponse<String>> {
    let user: User = {username: username, password: password}
    return this.httpClient.post<String>(this.baseUrl + "/login", user, {observe: 'response'})
  }
}
