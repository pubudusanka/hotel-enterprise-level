import { Component } from '@angular/core';
import {MainHeaderComponent} from './inner-items/main-header/main-header.component';
import {OptionsBarComponent} from './inner-items/options-bar/options-bar.component';
import {StateContextComponent} from './inner-items/state-context/state-context.component';
import {AppOffersComponent} from './inner-items/app-offers/app-offers.component';
import {
  HomeTrendingDestinationsComponent
} from './inner-items/home-trending-destinations/home-trending-destinations.component';

@Component({
  selector: 'app-home-page',
  imports: [
    OptionsBarComponent,
    StateContextComponent,
    AppOffersComponent,
    HomeTrendingDestinationsComponent
  ],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.scss',
  standalone: true
})
export class HomePageComponent {

}
