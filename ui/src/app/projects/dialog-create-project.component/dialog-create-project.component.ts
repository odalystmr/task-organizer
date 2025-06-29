import {Component} from '@angular/core';
import {Project} from "../../interfaces/project.interface";
import {MatDialogRef} from "@angular/material/dialog";
import {DialogAddParticipantsComponent} from "../dialog-add-participants/dialog-add-participants.component";
import {ProjectService} from "../../core/services/project.service";
import {CommonService} from "../../core/services/common.service";

@Component({
  selector: 'app-dialog-create-project.component',
  templateUrl: './dialog-create-project.component.html',
  styleUrls: ['./dialog-create-project.component.css']
})
export class DialogCreateProjectComponent {
  project: Project = {
    title: '',
    description: ''
  }

  constructor(private dialogRef: MatDialogRef<DialogAddParticipantsComponent>, private projectService: ProjectService,
              private commonService: CommonService) {
  }

  close() {
    this.dialogRef.close();
  }

  createProject() {
    this.projectService.createProject(this.project)
      .subscribe((response) => {
        this.dialogRef.close()
        this.commonService.reloadComponent('/project/' + response.id)
      });

  }
}
