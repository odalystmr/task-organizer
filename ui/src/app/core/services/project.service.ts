import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {Project} from "../../interfaces/project.interface";
import {CookieService} from "ngx-cookie-service";

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  constructor(private http: HttpClient, private cookieService: CookieService) {
  }

  private apiUrl: string = environment.apiUrl;

  private getToken(): string {
    return this.cookieService.get('token');
  }

  getProjects(): Observable<Project[]> {
    return this.http.get<Project[]>(`${this.apiUrl}projects`, {
      headers: new HttpHeaders({'Authorization': 'Bearer ' + this.getToken()})
    });
  }

  getProjectById(id: string): Observable<Project> {
    return this.http.get<Project>(`${this.apiUrl}projects/${id}`, {
      headers: new HttpHeaders({'Authorization': 'Bearer ' + this.getToken()})
    });
  }

  deleteProject(id: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}projects/${id}`, {
      headers: new HttpHeaders({'Authorization': 'Bearer ' + this.getToken()})
    });
  }

  addParticipants(projectId: number, newParticipantsUsernames: string[]): Observable<Project[]> {
    return this.http.post<Project[]>(`${this.apiUrl}projects/${projectId}/participants`, {participantUsernames: newParticipantsUsernames}, {
      headers: new HttpHeaders({'Authorization': 'Bearer ' + this.getToken()})
    });
  }

  createProject(project: Project) {
    return this.http.post<Project>(`${this.apiUrl}projects`, {
      title: project.title,
      description:project.description
    },{
      headers: new HttpHeaders({'Authorization': 'Bearer ' + this.getToken()})
    });
  }
}

