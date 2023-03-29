import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../../interfaces/user.interface";
import {environment} from "../../../environments/environment.development";
import {CookieService} from "ngx-cookie-service";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, private cookieService:CookieService) {
  }

  private baseUrl: string = environment.apiUrl;
  private getToken(): string {
    return this.cookieService.get('token');
  }
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.baseUrl}users`, {
      headers: new HttpHeaders({'Authorization': 'Bearer ' + this.getToken()})
    });
  }

  editUser(user: User): Observable<User> {
    return this.http.put<User>(`${this.baseUrl}users/${user.id}`, user, {
      headers: new HttpHeaders({'Authorization': 'Bearer ' + this.getToken()})
    });
  }

  deleteUser(id: number): Observable<any> {
    return this.http.delete<any>(`${this.baseUrl}users/${id}`, {
      headers: new HttpHeaders({'Authorization': 'Bearer ' + this.getToken()})
    });
  }

  getUsersByToken(token: string): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}auth/me`, {
      headers: new HttpHeaders({'Authorization': 'Bearer ' + token})
    });
  }

  postRegister(user: User): Observable<User> {
    return this.http.post<User>(`${this.baseUrl}auth/register`, user, {
      responseType: 'text' as 'json',
      headers: new HttpHeaders({'Accept': 'text/plain'})
    });
  }

  postLogin(username: string, password: string): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}auth/login`, {username, password}, {
      responseType: 'text' as 'json',
      headers: new HttpHeaders({'Accept': 'text/plain'})
    });
  }
}
