import { Injectable, signal } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class UserAuthService {
  private userId = signal<number>(0)
  
  getUserId() {
    return this.userId()
  }

  setUserId(userId: number) {
    this.userId.set(userId)
  }
  
}
