import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ContainerComponent} from "../common/container/container.component";
import {ProjectListComponent} from "./project-list/project-list.component";
import {UserHasLoggedInGuard} from "../core/guards/user-has-logged-in.guard";
import {ProjectDetailsComponent} from "./project-details/project-details.component";
import {ProjectComponent} from "./project/project.component";

const routes: Routes = [
  {
    path: '',
    component: ContainerComponent,
    canActivate: [UserHasLoggedInGuard],
    children: [
      {
        path: '',
        component: ProjectListComponent
      },
      {
        path: 'project/:id',
        component: ProjectComponent
      }
    ]
  }];

    @NgModule({
      imports: [RouterModule.forChild(routes)],
      exports: [RouterModule]
    })
    export class ProjectsRoutingModule {
    }
