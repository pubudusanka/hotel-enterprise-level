import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {FooterComponentComponent} from './components/footer-component/footer-component.component';
import {MainHeaderComponent} from "./pages/home-page/inner-items/main-header/main-header.component";

@Component({
  selector: 'app-root',
    imports: [RouterOutlet, FooterComponentComponent, MainHeaderComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
  standalone: true
})
export class AppComponent {
  title = 'hotel-client';
}
