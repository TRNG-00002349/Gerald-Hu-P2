import { Component, computed, inject, Signal, signal } from '@angular/core';
import { ApiClient } from '../api-client';
import { UserAuthService } from '../user-auth-service/user-auth-service';
import { ActivatedRoute } from '@angular/router';
import { User } from '../user';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-user-profile',
  imports: [],
  templateUrl: './user-profile.html',
  styleUrl: './user-profile.css',
})
export class UserProfile {

  userId = signal<number>(0);
  userAuthService = inject(UserAuthService)
  apiClient = inject(ApiClient)
  activatedRoute = inject(ActivatedRoute)
  user = signal<User>({})
  err = signal<string>("")
  errStatus = signal<number>(0)

  loggedInUser = computed(() => {
    return this.userAuthService.getUserId()
  })

  constructor() {
    this.activatedRoute.params.subscribe((params) => {
      console.log("helop meeeeeeee", params)
      this.userId.set(Number(params['id']))
    })

    this.apiClient.getUser(this.userId())
      .subscribe({
        next: (body: User) => {
          console.log(body)
          this.user.set(body)
        },
        error: (err: HttpErrorResponse) => {
          console.log(err)
          this.errStatus.set(err.status)
          this.err.set(String(err))
        }
      })
  }
}
