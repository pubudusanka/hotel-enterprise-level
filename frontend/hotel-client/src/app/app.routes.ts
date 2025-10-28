import { Routes } from '@angular/router';
import {HomePageComponent} from './pages/home-page/home-page.component';
import {NotFoundPageComponent} from './pages/not-found-page/not-found-page.component';
import {SecurityContextComponent} from './pages/security/security-context/security-context.component';
import {LoginComponent} from './pages/security/login/login.component';

export const routes: Routes = [
  {path:'', redirectTo: '/home', pathMatch: 'full'},
  {path:'home', component:HomePageComponent},
  {
    path:'security', component:SecurityContextComponent,children: [
      {path:'', redirectTo: '/security/login', pathMatch: 'full'},
      {path:'login', component:LoginComponent},
    ]
  },
  {path:'**', component:NotFoundPageComponent}
];
