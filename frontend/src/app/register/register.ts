import { Component, signal, WritableSignal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ApiClient, ApiResponse } from '../api-client';
import { HttpErrorResponse, HttpResponse, HttpStatusCode } from '@angular/common/http';

export enum RegistrationState {
  NOT_STARTED = "NOT_STARTED",
  SUCCESS = "SUCCESS",
  FAILURE = "FAILURE"
}

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
  registrationState: WritableSignal<RegistrationState> = signal(RegistrationState.NOT_STARTED)

  constructor(apiClient: ApiClient) {
    this.apiClient = apiClient
  }

  submitRegistrationForm(): void {
    console.log("submitting registration form...")
    this.apiClient.register(this.username, this.password, this.email).subscribe({
      next: (body: ApiResponse) => {
        console.log(body)
        this.registrationResult.set(`Created! Your user ID is ${body.id}`)
        this.registrationState.set(RegistrationState.SUCCESS)
      },
      error: (err: HttpErrorResponse) => {
        console.log(err)
        this.registrationResult.set(err?.error?.error)
        this.registrationState.set(RegistrationState.FAILURE)
      }
    })
  }
}
