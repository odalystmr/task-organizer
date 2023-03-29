import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ProjectsRoutingModule} from './projects-routing.module';
import {ProjectDetailsComponent} from './project-details/project-details.component';
import { ProjectListComponent } from './project-list/project-list.component';
import {MatCardModule} from "@angular/material/card";
import {MatTableModule} from "@angular/material/table";
import {MatInputModule} from "@angular/material/input";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import {MaterialModule} from "../material/material.module";
import {FlexModule} from "@angular/flex-layout";
import {DialogAddParticipantsComponent} from "./dialog-add-participants/dialog-add-participants.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatExpansionModule} from "@angular/material/expansion";
import { ProjectComponent } from './project/project.component';
import { DialogCreateTaskComponent } from './dialog-create-task.component/dialog-create-task.component';
import { DialogCreateTaskListComponent } from './dialog-create-task-list.component/dialog-create-task-list.component';
import { DialogCreateProjectComponent } from './dialog-create-project.component/dialog-create-project.component';


@NgModule({
  declarations: [
    ProjectDetailsComponent,
    ProjectListComponent,
    DialogAddParticipantsComponent,
    ProjectComponent,
    DialogCreateTaskComponent,
    DialogCreateTaskListComponent,
    DialogCreateProjectComponent

  ],
  imports: [
    CommonModule,
    ProjectsRoutingModule,
    MatCardModule,
    MatTableModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule,
    MaterialModule,
    FlexModule,
    FormsModule,
    ReactiveFormsModule,
    MatExpansionModule,

  ]
})
export class ProjectsModule { }
