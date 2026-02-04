import { Component } from '@angular/core';
import { Register } from '../register/register';
import { Login } from '../login/login';

@Component({
  selector: 'app-register-login',
  imports: [Register, Login],
  templateUrl: './register-login.html',
  styleUrl: './register-login.css',
})
export class RegisterLogin {

}
