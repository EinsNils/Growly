import { Routes } from '@angular/router';
import {Login} from './auth/login/login';
import {Home} from './home/home';
import {Register} from './auth/register/register';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'login', component: Login },
  { path: 'auth/register', component: Register },
];
