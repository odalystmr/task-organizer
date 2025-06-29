import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {DialogAddParticipantsComponent} from "../dialog-add-participants/dialog-add-participants.component";
import {UserService} from "../../core/services/user.service";
import {TaskList} from "../../interfaces/task-list.interface";
import {TaskListService} from "../../core/services/task-list.service";

@Component({
  selector: 'app-dialog-create-task-list.component',
  templateUrl: './dialog-create-task-list.component.html',
  styleUrls: ['./dialog-create-task-list.component.css']
})
export class DialogCreateTaskListComponent implements OnInit {
  taskList: TaskList = {
    title: '',
    position: 0
  }

  constructor(private dialogRef: MatDialogRef<DialogAddParticipantsComponent>, private taskListService: TaskListService,
              @Inject(MAT_DIALOG_DATA)
              public data: { projectId: number}) {}

  ngOnInit(): void {

  }


  close() {
    this.dialogRef.close();
  }

  createTaskList() {
    this.taskListService
      .createTaskList(this.data.projectId, this.taskList)
      .subscribe(() => this.dialogRef.close());

  }

}
