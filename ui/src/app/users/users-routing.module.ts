import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {UserHasLoggedInGuard} from "../core/guards/user-has-logged-in.guard";
import {ContainerComponent} from "../common/container/container.component";
import {ProfileComponent} from "./components/profile/profile.component";

const routes: Routes = [
  {
    path:'',
    children:[
      {
        path:'login',
        component: LoginComponent
      },
      {
        path:'register',
        component: RegisterComponent
      }
    ]
  },
  {
    path:'',
    component: ContainerComponent,
    canActivate:[UserHasLoggedInGuard],
    children:[
      {
        path:'profile',
        component: ProfileComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsersRoutingModule { }
