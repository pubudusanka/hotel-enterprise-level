import { Component } from '@angular/core';
import {MatIcon} from '@angular/material/icon';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-main-header',
  imports: [
    MatIcon,
    RouterLink
  ],
  templateUrl: './main-header.component.html',
  styleUrl: './main-header.component.scss',
  standalone:true
})
export class MainHeaderComponent {

}
