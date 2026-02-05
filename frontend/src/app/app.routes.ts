import { Routes } from '@angular/router';
import { RegisterLogin } from './register-login/register-login';
import { Frontpage } from './frontpage/frontpage'
import { UserProfile } from './user-profile/user-profile';

export const routes: Routes = [
  {
    path: '',
    component: Frontpage
  },
  {
    path: 'register-login',
    component: RegisterLogin
  },
  {
    path: 'users/:id',
    component: UserProfile
  }
];
