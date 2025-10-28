import {ChangeDetectionStrategy, Component} from '@angular/core';
import {MatIcon} from '@angular/material/icon';
import {provideNativeDateAdapter} from '@angular/material/core';
import {MatMenu, MatMenuItem, MatMenuTrigger} from '@angular/material/menu';

@Component({
  selector: 'app-stays-form',
  imports: [
    MatIcon,
    MatMenu,
    MatMenuItem,
    MatMenuTrigger,
  ],
  providers: [provideNativeDateAdapter()],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './stays-form.component.html',
  styleUrl: './stays-form.component.scss',
  standalone:true
})
export class StaysFormComponent {

}
