import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {CookieService} from "ngx-cookie-service";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {Project} from "../../interfaces/project.interface";
import {TaskList} from "../../interfaces/task-list.interface";
import {Task} from "../../interfaces/task.interface";

@Injectable({
  providedIn: 'root'
})
export class TaskListService {
  constructor(private http: HttpClient, private cookieService: CookieService) {
  }

  private apiUrl: string = environment.apiUrl;

  private getToken(): string {
    return this.cookieService.get('token');
  }

  getTaskLists(projectId:number): Observable<TaskList[]> {
    return this.http.get<TaskList[]>(`${this.apiUrl}projects/${projectId}/task-lists`, {
      headers: new HttpHeaders({'Authorization': 'Bearer ' + this.getToken()})
    });
  }
  createTaskList(projectId: number, taskList:TaskList): Observable<Task> {
    return this.http.post<Task>(`${this.apiUrl}projects/${projectId}/task-lists`, {
      title: taskList.title,
      position:0,
    }, {
      headers: new HttpHeaders({'Authorization': 'Bearer ' + this.getToken()})
    });
  }

  deleteTaskList(projectId: number, taskListId: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}projects/${projectId}/task-lists/${taskListId}`, {
      headers: new HttpHeaders({'Authorization': 'Bearer ' + this.getToken()})
    });
  }
}
