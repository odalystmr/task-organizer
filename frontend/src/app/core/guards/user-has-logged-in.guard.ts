import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {UserService} from "../services/user.service";
import {CookieService} from "ngx-cookie-service";

@Injectable({
  providedIn: 'root'
})
export class UserHasLoggedInGuard implements CanActivate {
  constructor(private userService: UserService, private cookieService: CookieService, private router: Router) {
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const redirect = this.router.parseUrl('/login');

    const token = this.cookieService.get('token');
    if (!token) {
      return redirect;
    }

    return new Observable<boolean | UrlTree>((observer) => {
      this.userService.getUsersByToken(token).subscribe(user => {
        if (user) {
          observer.next(true);
          return;
        }

        observer.next(redirect);
      });
    });
  }

}
