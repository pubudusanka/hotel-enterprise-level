import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResetPwVerificationComponent } from './reset-pw-verification.component';

describe('ResetPwVerificationComponent', () => {
  let component: ResetPwVerificationComponent;
  let fixture: ComponentFixture<ResetPwVerificationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ResetPwVerificationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ResetPwVerificationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
