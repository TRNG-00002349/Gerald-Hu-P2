import { Component, computed, inject, Signal } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { UserAuthService } from '../user-auth-service/user-auth-service';

@Component({
  selector: 'app-navbar',
  imports: [RouterModule],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})
export class Navbar {

  userAuthService = inject(UserAuthService)

  userProfileLink = computed(() => {
    // console.log("how many times is this called, eh")
    const userId: number = this.userAuthService.getUserId() || 0
    if (userId) {
      return `users/${userId}`
    } return ``
  })
}
