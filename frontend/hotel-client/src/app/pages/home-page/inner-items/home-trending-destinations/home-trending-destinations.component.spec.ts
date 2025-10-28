import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeTrendingDestinationsComponent } from './home-trending-destinations.component';

describe('HomeTrendingDestinationsComponent', () => {
  let component: HomeTrendingDestinationsComponent;
  let fixture: ComponentFixture<HomeTrendingDestinationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HomeTrendingDestinationsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomeTrendingDestinationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
