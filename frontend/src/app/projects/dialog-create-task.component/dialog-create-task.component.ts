import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {DialogAddParticipantsComponent} from "../dialog-add-participants/dialog-add-participants.component";
import {TaskService} from "../../core/services/task.service";
import {Task} from "../../interfaces/task.interface";
import {User} from "../../interfaces/user.interface";
import {UserService} from "../../core/services/user.service";
import {map, Observable, startWith} from "rxjs";
import {FormControl} from "@angular/forms";
import {MatOptionSelectionChange} from "@angular/material/core";

@Component({
  selector: 'app-dialog-create-task.component',
  templateUrl: './dialog-create-task.component.html',
  styleUrls: ['./dialog-create-task.component.css']
})
export class DialogCreateTaskComponent implements OnInit {
  task: Task = {
    title: '',
    description: '',
    position: 0,
    complete: false
  }
  options: string[] = [];
  users: User[] = [];
  filteredOptions: Observable<string[]> | undefined;
  myControl = new FormControl('');

  constructor(private dialogRef: MatDialogRef<DialogAddParticipantsComponent>, private taskService: TaskService,
              private userService: UserService,
              @Inject(MAT_DIALOG_DATA) public data: { projectId: number, taskListId: number }) {
  }

  ngOnInit(): void {
    this.userService.getUsers().subscribe({
      next: (response) => {
        for (let user of response) {
          this.options.push(user.username);
        }
        this.users = response;
      }
    })

    this.filteredOptions = this.myControl.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value || '')
      ),
    );
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.options.filter(option => option.toLowerCase().includes(filterValue));
  }

  onSelectAssignee(event: MatOptionSelectionChange) {
    this.task.assignee =
      this.users.filter(user => user.username === event.source.value)[0];
  }

  close() {
    this.dialogRef.close();
  }

  createTask() {
    this.taskService
      .createTask(this.data.projectId, this.data.taskListId, this.task)
      .subscribe(() => this.dialogRef.close());

  }
}
