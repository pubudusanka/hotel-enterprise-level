import {ChangeDetectionStrategy, Component} from '@angular/core';
import {MatIcon} from '@angular/material/icon';
import {provideNativeDateAdapter} from '@angular/material/core';
import {MatMenu, MatMenuItem, MatMenuTrigger} from '@angular/material/menu';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-stays-form',
  imports: [
    MatIcon,
    MatMenu,
    MatMenuTrigger,
    RouterLink,
  ],
  providers: [provideNativeDateAdapter()],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './stays-form.component.html',
  styleUrl: './stays-form.component.scss',
  standalone:true
})
export class StaysFormComponent {

}
