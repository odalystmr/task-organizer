import {Component, OnInit} from '@angular/core';
import {CookieService} from "ngx-cookie-service";
import {Router} from "@angular/router";
import {UserService} from "../../core/services/user.service";

@Component({
  selector: 'app-home',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {

  name: string ='';

  constructor(private cookieService: CookieService, private router: Router, private userService: UserService) {
  }

  ngOnInit(): void {
    const token = this.cookieService.get('token');
    this.userService.getUsersByToken(token).subscribe(response => this.name = response.username);
  }

  logOut() {
    this.cookieService.delete('token');
    this.router.navigateByUrl('/login');
  }
}
