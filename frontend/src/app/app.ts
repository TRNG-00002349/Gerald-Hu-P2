import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { RegisterLogin } from "./register-login/register-login";
import { Navbar } from "./navbar/navbar";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RegisterLogin, Navbar],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('frontend');
}
