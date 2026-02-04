import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './user';

export type ApiResponse = {
  createdAt?: Date,
  updatedAt?: Date,
  id: number
}

@Injectable({
  providedIn: 'root',
})
export class ApiClient {
  baseUrl: string = "" //baseURL is blank for now thanks to the proxy to make http work. We'll have to do something with this later.
  httpClient: HttpClient

  constructor(httpClient: HttpClient) {
    this.httpClient = httpClient
  }

  register(username: string, password: string, email: string): Observable<ApiResponse> {
    let user: User = {username: username, password: password, email: email}
    return this.httpClient.post<ApiResponse>(this.baseUrl+"/register", user)
  }

  login(username: string, password: string): Observable<ApiResponse> {
    let user: User = {username: username, password: password}
    return this.httpClient.post<ApiResponse>(this.baseUrl + "/login", user)
  }
}
