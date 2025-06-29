import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Task} from 'src/app/interfaces/task.interface';
import {ProjectService} from "../../core/services/project.service";
import {TaskList} from "../../interfaces/task-list.interface";
import {TaskListService} from "../../core/services/task-list.service";
import {TaskService} from "../../core/services/task.service";
import {Project} from "../../interfaces/project.interface";
import {DialogCreateTaskComponent} from "../dialog-create-task.component/dialog-create-task.component";
import {MatDialog} from "@angular/material/dialog";
import {CommonService} from "../../core/services/common.service";
import {DialogCreateTaskListComponent} from "../dialog-create-task-list.component/dialog-create-task-list.component";

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {

  taskLists: TaskList[] = [];
  tasks: Task[] = [];
  project: Project={
    title:'',
    description:''
  };

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private dialog: MatDialog, private commonService: CommonService,
              private projectService: ProjectService, private taskListsService: TaskListService, private taskService: TaskService) {
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(({id}) => {
      this.projectService.getProjectById(id).subscribe(project => this.project = project)

      this.taskListsService.getTaskLists(id).subscribe({
        next: (response) => {
          this.taskLists = response;
        }
      })
    });
  }

  createTask(taskListId: number) {
    const dialog = this.dialog.open(
      DialogCreateTaskComponent,
      {
        data: {projectId: this.project.id, taskListId: taskListId}
      }
    )

    dialog.afterClosed().subscribe(
      () => this.commonService.reloadComponent('/project/' + this.project.id)
    )
  }

  editTask(taskListId: number, task: Task) {

    this.taskService.editTask(this.project.id!, taskListId, task)
      .subscribe();
  }

  deleteTask(taskListId: number, taskId: number) {
    this.taskService.deleteTask(this.project.id!, taskListId, taskId)
      .subscribe(() => this.commonService.reloadComponent('/project/' + this.project.id));
  }

  createTaskList() {
    const dialog = this.dialog.open(
      DialogCreateTaskListComponent,
      {
        data: {projectId: this.project.id}
      }
    )

    dialog.afterClosed().subscribe(
      () => this.commonService.reloadComponent('/project/' + this.project.id)
    )
  }

  editTaskList(number: number) {

  }

  deleteTaskList(taskListId: number) {
    this.taskListsService.deleteTaskList(this.project.id!, taskListId)
      .subscribe(() => this.commonService.reloadComponent('/project/' + this.project.id));
  }

  editProject() {
    // this.project.id
  }

  deleteProject() {
    this.projectService.deleteProject(this.project.id!)
      .subscribe(() => this.commonService.reloadComponent('/'));
  }
}
