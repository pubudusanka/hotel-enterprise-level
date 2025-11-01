import { Component } from '@angular/core';
import {StaysFormComponent} from '../home-page/inner-items/stays-form/stays-form.component';
import {HotelCardComponent} from './inner/hotel-card/hotel-card.component';

@Component({
  selector: 'app-search-result',
  imports: [
    StaysFormComponent,
    HotelCardComponent
  ],
  templateUrl: './search-result.component.html',
  styleUrl: './search-result.component.scss',
  standalone:true
})
export class SearchResultComponent {

}
