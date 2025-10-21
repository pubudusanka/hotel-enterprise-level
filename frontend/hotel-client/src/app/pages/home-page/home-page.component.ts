import { Component } from '@angular/core';
import {MainHeaderComponent} from './inner-items/main-header/main-header.component';
import {OptionsBarComponent} from './inner-items/options-bar/options-bar.component';

@Component({
  selector: 'app-home-page',
  imports: [
    MainHeaderComponent,
    OptionsBarComponent
  ],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.scss',
  standalone: true
})
export class HomePageComponent {

}
