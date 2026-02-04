import { Component, signal, WritableSignal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ApiClient, ApiResponse } from '../api-client';
import { HttpErrorResponse, HttpResponse, HttpStatusCode } from '@angular/common/http';

@Component({
  selector: 'app-register',
  imports: [FormsModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {
  apiClient: ApiClient
  username: string = ""
  password: string = ""
  email: string = ""

  registrationResult: WritableSignal<String> = signal("")

  constructor(apiClient: ApiClient) {
    this.apiClient = apiClient
  }

  submitRegistrationForm(): void {
    console.log("submitting registration form...")
    this.apiClient
      .register(this.username, this.password, this.email)
      .subscribe({
        next: (body: ApiResponse) => {
          console.log(body)
          this.registrationResult.set(`Created! Your user ID is ${body.id}`)
        },
        error: (err: HttpErrorResponse) => {
          console.log(err)
          this.registrationResult.set(`Couldn't create user: ${err?.error?.error}`)
        }
      })
  }
}
