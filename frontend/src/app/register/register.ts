import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ApiClient } from '../api-client';

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

  constructor(apiClient: ApiClient) {
    this.apiClient = apiClient
  }

  submitRegistrationForm(): void {
    console.log("submitting registration form...")
    this.apiClient.register(this.username, this.password, this.email).subscribe((response) => {
      console.log(response)
    })
  }
}
