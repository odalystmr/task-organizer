import {Component, OnInit} from '@angular/core';
import {User} from "../../../interfaces/user.interface";
import {UserService} from "../../../core/services/user.service";
import {CookieService} from "ngx-cookie-service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  user: User = {
    fullName:'',
    username: '',
    email: '',
    password: '',
  }

  constructor(private userService: UserService, private cookieService: CookieService, private router:Router) {
  }

  ngOnInit(): void {
  }

  login() {
    this.userService.postLogin(this.user.username, this.user.password).subscribe({
        next: response => {
          this.cookieService.set('token',response, {path: '/'});
          this.router.navigateByUrl('');
        }
      }
    );


  }

}
