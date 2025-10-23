import { Component } from '@angular/core';
import {StaysFormComponent} from '../stays-form/stays-form.component';

@Component({
  selector: 'app-state-context',
  imports: [
    StaysFormComponent
  ],
  templateUrl: './state-context.component.html',
  styleUrl: './state-context.component.scss',
  standalone: true
})
export class StateContextComponent {

}
