import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatDialog} from "@angular/material/dialog";
import {UserService} from "../../../core/services/user.service";
import {CookieService} from "ngx-cookie-service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user = {
    fullName: '',
    username: '',
    email: '',
    password: '',
    confirmPassword: ''
  }

  constructor(private userService: UserService, private activatedRoute: ActivatedRoute, private router: Router,
              private snackBar: MatSnackBar, private dialog: MatDialog, private cookieService: CookieService) {
  }

  ngOnInit(): void {

  }

  save() {
    if (
      !this.user.fullName
      || this.user.fullName.trim().length === 0
      || !this.user.email
      || this.user.email.trim().length === 0
      || !this.user.username
      || this.user.username.trim().length === 0
      || !this.user.password
      || this.user.password.trim().length === 0
      || !this.user.confirmPassword
      || this.user.confirmPassword.trim() !== this.user.password.trim()
    ) {
      return;
    }

    this.userService.postRegister(this.user).subscribe({
      next: () => {
        this.showSnackBar(`'${this.user.username}' creado correctamente`);
        this.router.navigateByUrl('/login')
      },
      error: (err) => {
        console.error(err)
        this.showSnackBar('No se pudo crear el usuario')
      }
    });
  }

  showSnackBar(msg: string) {
    this.snackBar.open(
      msg,
      'Aceptar',
      {
        duration: 3000
      }
    )
  }
}
