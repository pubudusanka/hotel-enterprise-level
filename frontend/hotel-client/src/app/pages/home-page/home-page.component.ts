import { Component } from '@angular/core';
import {MainHeaderComponent} from './inner-items/main-header/main-header.component';

@Component({
  selector: 'app-home-page',
  imports: [
    MainHeaderComponent
  ],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.scss',
  standalone: true
})
export class HomePageComponent {

}
