import {User} from "./user.interface";

export interface Project {
  id?:number,
  title: string,
  description:string,
  owner?: User,
  participants?:User[]
}
