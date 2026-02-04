import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { RegisterLogin } from "./register-login/register-login";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RegisterLogin],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('frontend');
}
