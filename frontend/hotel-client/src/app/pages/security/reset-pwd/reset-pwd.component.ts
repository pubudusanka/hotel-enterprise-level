import { Component } from '@angular/core';
import {MatButton} from '@angular/material/button';
import {MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {RouterLink} from '@angular/router';
import {MatCheckbox} from '@angular/material/checkbox';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-reset-pwd',
  imports: [
    MatButton,
    MatFormField,
    MatInput,
    RouterLink,
    MatFormField,
    MatCheckbox,
    FormsModule,
    MatLabel
  ],
  templateUrl: './reset-pwd.component.html',
  styleUrl: './reset-pwd.component.scss',
  standalone:true
})
export class ResetPwdComponent {
  showStatus=false;
}
