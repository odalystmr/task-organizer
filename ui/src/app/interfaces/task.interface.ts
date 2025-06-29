import {User} from "./user.interface";

export interface Task{
  id?: number,
  title:string,
  description:string,
  position:number,
  complete:boolean,
  assignee?:User,
  taskListId?:number
}
