import { DiscussionPost } from "./discussion-post";

export interface User{

    id:number,
    email:string,
    password:string,
    type:string,
    posts: DiscussionPost[]

}