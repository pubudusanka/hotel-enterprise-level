import { Component } from '@angular/core';
import {MatButton} from '@angular/material/button';
import {MatIcon} from '@angular/material/icon';

@Component({
  selector: 'app-options-bar',
  imports: [
    MatIcon
  ],
  templateUrl: './options-bar.component.html',
  styleUrl: './options-bar.component.scss',
  standalone: true
})
export class OptionsBarComponent {

}
