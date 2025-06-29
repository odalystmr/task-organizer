import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {CookieService} from "ngx-cookie-service";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {Task} from 'src/app/interfaces/task.interface';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private http: HttpClient, private cookieService: CookieService) {
  }

  private apiUrl: string = environment.apiUrl;

  private getToken(): string {
    return this.cookieService.get('token');
  }

  getTasks(projectId: number, taskListId: number): Observable<Task[]> {
    return this.http.get<Task[]>(`${this.apiUrl}projects/${projectId}/task-lists/${taskListId}/tasks`, {
      headers: new HttpHeaders({'Authorization': 'Bearer ' + this.getToken()})
    });
  }

  createTask(projectId: number, taskListId: number, task: Task): Observable<Task> {
    return this.http.post<Task>(`${this.apiUrl}projects/${projectId}/task-lists/${taskListId}/tasks`, {
      title: task.title,
      description: task.description,
      position: 0,
      complete: false,
      assigneeId: task.assignee?.id
    }, {
      headers: new HttpHeaders({'Authorization': 'Bearer ' + this.getToken()})
    });
  }

  editTask(projectId: number, taskListId: number, task: Task): Observable<Task> {
    return this.http.patch<Task>(`${this.apiUrl}projects/${projectId}/task-lists/${taskListId}/tasks/${task.id}`, {
      complete: task.complete
    }, {
      headers: new HttpHeaders({'Authorization': 'Bearer ' + this.getToken()})
    });
  }

  deleteTask(projectId: number, taskListId: number, taskId: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}projects/${projectId}/task-lists/${taskListId}/tasks/${taskId}`, {
      headers: new HttpHeaders({'Authorization': 'Bearer ' + this.getToken()})
    });
  }


}
