import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Project} from "../../interfaces/project.interface";
import {User} from "../../interfaces/user.interface";
import {FormControl} from "@angular/forms";
import {map, Observable, startWith} from "rxjs";
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../../core/services/user.service";
import {MatOptionSelectionChange} from "@angular/material/core";
import {ProjectService} from "../../core/services/project.service";
import {CommonService} from "../../core/services/common.service";

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog-add-participants.component.html',
  styleUrls: ['./dialog-add-participants.component.css']
})
export class DialogAddParticipantsComponent implements OnInit {
  myControl = new FormControl('');
  options: string[] = [];
  selectedParticipants: string[] = [];
  filteredOptions: Observable<string[]> | undefined;

  constructor(private dialogRef: MatDialogRef<DialogAddParticipantsComponent>, private activatedRoute: ActivatedRoute, private userService: UserService, private projectService: ProjectService,
              private commonService:CommonService,
              @Inject(MAT_DIALOG_DATA) public dataProject: Project, @Inject(MAT_DIALOG_DATA) public dataUser: User) {
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(() => {
      this.userService.getUsers().subscribe({
        next: (response) => {
          for (let user of response)
            this.options.push(user.username);
        }
      })
    })
    this.filteredOptions = this.myControl.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value || '')),
    );
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.options.filter(option => option.toLowerCase().includes(filterValue));
  }

  addParticipants() {
    this.projectService
      .addParticipants(this.dataProject.id!, this.selectedParticipants)
      .subscribe({
        next: response => {
          this.dialogRef.close(response);
          this.commonService.reloadComponent('/');
        }
      });
  }

  close() {
    this.dialogRef.close();
  }

  onSelectParticipant(event: MatOptionSelectionChange) {
    this.selectedParticipants.push(event.source.value);
  }
}
