import { Routes } from '@angular/router';
import {HomePageComponent} from './pages/home-page/home-page.component';
import {NotFoundPageComponent} from './pages/not-found-page/not-found-page.component';
import {SecurityContextComponent} from './pages/security/security-context/security-context.component';
import {LoginComponent} from './pages/security/login/login.component';
import {RegisterComponent} from './pages/security/register/register.component';
import {RegisterVerificationComponent} from './pages/security/register-verification/register-verification.component';
import {ForgotPasswordComponent} from './pages/security/forgot-password/forgot-password.component';

export const routes: Routes = [
  {path:'', redirectTo: '/home', pathMatch: 'full'},
  {path:'home', component:HomePageComponent},
  {
    path:'security', component:SecurityContextComponent,children: [
      {path:'', redirectTo: '/security/login', pathMatch: 'full'},
      {path:'login', component:LoginComponent},
      {path:'register', component: RegisterComponent},
      {path:'register-verification/:email', component:RegisterVerificationComponent},
      {path:'forgot-password', component:ForgotPasswordComponent},
    ]
  },
  {path:'**', component:NotFoundPageComponent}
];
