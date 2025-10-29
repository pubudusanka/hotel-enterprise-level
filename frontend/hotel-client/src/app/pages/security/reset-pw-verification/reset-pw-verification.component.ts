import { Component } from '@angular/core';
import {MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {MatButton} from '@angular/material/button';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-reset-pw-verification',
  imports: [
    MatFormField,
    MatInput,
    MatButton,
    RouterLink,
    MatLabel
  ],
  templateUrl: './reset-pw-verification.component.html',
  styleUrl: './reset-pw-verification.component.scss',
  standalone:true
})
export class ResetPwVerificationComponent {

}
