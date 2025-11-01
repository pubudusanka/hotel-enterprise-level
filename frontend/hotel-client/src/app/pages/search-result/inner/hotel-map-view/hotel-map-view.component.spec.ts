import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelMapViewComponent } from './hotel-map-view.component';

describe('HotelMapViewComponent', () => {
  let component: HotelMapViewComponent;
  let fixture: ComponentFixture<HotelMapViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HotelMapViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HotelMapViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
