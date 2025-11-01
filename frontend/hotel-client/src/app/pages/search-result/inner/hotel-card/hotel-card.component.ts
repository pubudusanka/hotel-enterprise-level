import {Component, inject} from '@angular/core';
import {MatIcon} from '@angular/material/icon';
import {MatTooltip} from '@angular/material/tooltip';
import {MatButton} from '@angular/material/button';
import {MatDialog} from '@angular/material/dialog';
import {HotelMapViewComponent} from '../hotel-map-view/hotel-map-view.component';

@Component({
  selector: 'app-hotel-card',
  imports: [
    MatIcon,
    MatTooltip,
    MatButton,
  ],
  templateUrl: './hotel-card.component.html',
  styleUrl: './hotel-card.component.scss',
  standalone:true
})
export class HotelCardComponent {
  position: any;

  dialog = inject(MatDialog);

  openModel(){
    this.dialog.open(HotelMapViewComponent,{
      width:'1000px',
      data:{},
      disableClose:true //close only press the close button
    })
  }

}
