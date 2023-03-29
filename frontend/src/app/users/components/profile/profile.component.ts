import {Component, OnInit} from '@angular/core';
import {User} from 'src/app/interfaces/user.interface';
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatDialog} from "@angular/material/dialog";
import {UserService} from "../../../core/services/user.service";
import {switchMap} from "rxjs";
import {CookieService} from "ngx-cookie-service";
import {DialogComponent} from "../../../common/dialog/dialog.component";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user: User = {
    fullName: '',
    username: '',
    email: '',
    password: ''
  }

  constructor(private userService: UserService, private activatedRoute: ActivatedRoute, private router: Router,
              private snackBar: MatSnackBar, private dialog: MatDialog, private cookieService: CookieService) {
  }

  ngOnInit(): void {

    if (!this.router.url.includes('profile')) {
      return;
    }

    const token = this.cookieService.get('token');

    this.activatedRoute.params.pipe(
      switchMap(() => this.userService.getUsersByToken(token))
    ).subscribe(response => this.user = response)

  }

  save() {
    if (
      !this.user.fullName
      || this.user.fullName.trim().length === 0
      || !this.user.email
      || this.user.email.trim().length === 0
      || !this.user.username
      || this.user.username.trim().length === 0
    ) {
      return;
    }

    this.userService.editUser(this.user).subscribe({
      next: () => {
        this.showSnackBar(`'${this.user.username}' actualizado correctamente`);
      },
      error: () => this.showSnackBar('No se pudo editar el usuario')
    })
  }

  delete() {
    this.userService.deleteUser(this.user.id!).subscribe({
      next: () => this.router.navigateByUrl('/login'),
      error: () => this.showSnackBar('No se pudo borrar al usuario')
    })
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
