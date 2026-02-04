import { Component, signal, WritableSignal } from '@angular/core';
import { ApiClient, ApiResponse } from '../api-client';
import { FormsModule } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  apiClient: ApiClient
  username: string = ""
  password: string = ""

  loginResult: WritableSignal<String> = signal("")

  constructor(apiClient: ApiClient) {
    this.apiClient = apiClient
  }

  submitLoginForm(): void {
    console.log("submitting login form...")
    this.apiClient
      .login(this.username, this.password)
      .subscribe({
        next: (body: ApiResponse) => {
          console.log(body)
          this.loginResult.set(`Logged in successfully!`)
        },
        error: (err: HttpErrorResponse) => {
          console.log(err)
          this.loginResult.set(`Couldn't log in: ${err?.error?.error}`)
        }
      })
  }
}
