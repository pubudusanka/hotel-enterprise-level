import { Component } from '@angular/core';
import {MainHeaderComponent} from './inner-items/main-header/main-header.component';
import {OptionsBarComponent} from './inner-items/options-bar/options-bar.component';
import {StateContextComponent} from './inner-items/state-context/state-context.component';

@Component({
  selector: 'app-home-page',
  imports: [
    MainHeaderComponent,
    OptionsBarComponent,
    StateContextComponent
  ],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.scss',
  standalone: true
})
export class HomePageComponent {

}
