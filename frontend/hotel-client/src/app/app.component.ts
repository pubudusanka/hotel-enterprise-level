import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {FooterComponentComponent} from './components/footer-component/footer-component.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, FooterComponentComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
  standalone: true
})
export class AppComponent {
  title = 'hotel-client';
}
