import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DiscussionPost } from '../models/discussion-post';

@Injectable({
  providedIn: 'root'
})
export class DisscussionBoardService {

  constructor(private httpClient: HttpClient) { }

  getAllPosts(): Observable<DiscussionPost[]> {
    return this.httpClient.get<DiscussionPost[]>("http://localhost:8000/discussion/post",{withCredentials: true});
  }

  createPost(post: DiscussionPost): Observable<HttpResponse<DiscussionPost>>{
    return this.httpClient.post<DiscussionPost>("http://localhost:8000/discussion/post",post,{withCredentials: true, observe: 'response' as 'response' });

  }
}
