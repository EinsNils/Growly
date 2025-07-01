import {Component, signal} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {

  /*
    username = signal<string>('');
  password = signal<string>('');

  constructor(private http: HttpClient) {
  }

  login() {
    const payload = {
      username: this.username(),
      password: this.password()
    };

    this.http.post('http://localhost:8080/auth/login', payload).subscribe({
      next: (res) => {
        console.log('Login successful', res);
      }
      , error: (err) => {
        console.error('Login failed', err);
      }
    })
  }
   */

}
