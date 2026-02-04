import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterLogin } from './register-login';

describe('RegisterLogin', () => {
  let component: RegisterLogin;
  let fixture: ComponentFixture<RegisterLogin>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegisterLogin]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegisterLogin);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
