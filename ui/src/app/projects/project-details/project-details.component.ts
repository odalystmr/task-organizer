import {Component, Input, OnInit} from '@angular/core';
import {Project} from "../../interfaces/project.interface";
import {ProjectService} from "../../core/services/project.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatDialog} from "@angular/material/dialog";
import {DialogAddParticipantsComponent} from "../dialog-add-participants/dialog-add-participants.component";
import {CommonService} from "../../core/services/common.service";

@Component({
  selector: 'app-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.css']
})
export class ProjectDetailsComponent implements OnInit {
  @Input()
  project!: Project;

  constructor(private projectService: ProjectService, private commonService: CommonService, private router: Router, private activatedRoute: ActivatedRoute, private snackBar: MatSnackBar, private dialog: MatDialog) {
  }

  ngOnInit(): void {
  }

  openDialogAddParticipants() {
    const dialog = this.dialog.open(
      DialogAddParticipantsComponent,
      {
        data: this.project
      }
    )

    dialog.afterClosed().subscribe(
      (result) => {
        this.commonService.reloadComponent('/')
      }
    )
  }

}
