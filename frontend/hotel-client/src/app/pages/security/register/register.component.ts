import { Component } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatButton} from '@angular/material/button';
import {MatCheckbox} from '@angular/material/checkbox';
import {MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [
    FormsModule,
    MatButton,
    MatCheckbox,
    MatFormField,
    MatInput,
    MatLabel,
    ReactiveFormsModule,
    RouterLink,
    MatFormField
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss',
  standalone:true
})
export class RegisterComponent {
  showStatus=false;

}
