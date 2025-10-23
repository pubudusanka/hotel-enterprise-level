import {ChangeDetectionStrategy, Component} from '@angular/core';
import {MatIcon} from '@angular/material/icon';
import {provideNativeDateAdapter} from '@angular/material/core';

@Component({
  selector: 'app-stays-form',
  imports: [
    MatIcon,
  ],
  providers: [provideNativeDateAdapter()],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './stays-form.component.html',
  styleUrl: './stays-form.component.scss',
  standalone:true
})
export class StaysFormComponent {

}
