import {Component, OnInit} from '@angular/core';
import {Project} from "../../interfaces/project.interface";
import {ProjectService} from "../../core/services/project.service";
import {DialogCreateProjectComponent} from "../dialog-create-project.component/dialog-create-project.component";
import {MatDialog} from "@angular/material/dialog";
import {CommonService} from "../../core/services/common.service";


@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {

  projects: Project[] = [];
  panelOpenState = false;

  constructor(private projectService: ProjectService, private dialog: MatDialog, private commonService: CommonService) {
  }

  ngOnInit(): void {
    this.projectService.getProjects().subscribe({
      next: (response) => {
        this.projects = response;
      }, error: () => {

      }
    });
  }


  createProject() {
    const dialog = this.dialog.open(
      DialogCreateProjectComponent
    )

    dialog.afterClosed().subscribe()
  }
}
