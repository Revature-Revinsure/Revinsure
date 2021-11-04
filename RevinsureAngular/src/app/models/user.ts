import { Type } from "@angular/core";
import { UserType } from "./user-type";

export interface User {
    id: number
    email: string;
    password: string;
    type: UserType;
}
