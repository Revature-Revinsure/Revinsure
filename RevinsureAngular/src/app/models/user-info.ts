import { User } from "./user";

export interface UserInfo {

    id: number;
    user: User;
    firstname: String;
    lastname: String;
    address: String;
    city: String;
    state: String;
    zip: String; //not a number?

}