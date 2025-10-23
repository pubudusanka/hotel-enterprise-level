import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StateContextComponent } from './state-context.component';

describe('StateContextComponent', () => {
  let component: StateContextComponent;
  let fixture: ComponentFixture<StateContextComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StateContextComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StateContextComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
