import {Task} from "./task.interface";

export interface TaskList {
  id?: number,
  title:string,
  position?:number,
  tasks?: Task[],
  projectId?:number

}
