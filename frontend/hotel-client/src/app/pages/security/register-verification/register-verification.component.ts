import {Component, inject, OnInit} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatButton} from '@angular/material/button';
import {MatCheckbox} from '@angular/material/checkbox';
import {MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {ActivatedRoute, RouterLink} from '@angular/router';

@Component({
  selector: 'app-register-verification',
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
  templateUrl: './register-verification.component.html',
  styleUrl: './register-verification.component.scss',
  standalone:true
})
export class RegisterVerificationComponent implements OnInit{

  // activatedRoute = inject(ActivatedRoute);

  email:any;
  constructor(private activatedRoute: ActivatedRoute) {

  }


  ngOnInit(): void {
    // this.email = this.activatedRoute.snapshot.paramMap.get('email');

    this.activatedRoute.paramMap.subscribe(response => {
      this.email = response.get('email');
    })

  }

}
