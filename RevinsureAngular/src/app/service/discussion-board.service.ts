import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DiscussionPost } from '../models/discussion-post';
import { DataService } from './data.service';

@Injectable({
  providedIn: 'root'
})
export class DisscussionBoardService {

  constructor(private httpClient: HttpClient, private dataService: DataService) { }

  baseUrl: string = this.dataService.baseURL;

  getAllPosts(): Observable<DiscussionPost[]> {
    return this.httpClient.get<DiscussionPost[]>(this.baseUrl + "/discussion/post",{withCredentials: true});
  }

  createPost(post: DiscussionPost): Observable<HttpResponse<DiscussionPost>>{
    return this.httpClient.post<DiscussionPost>(this.baseUrl + "/discussion/post",post,{withCredentials: true, observe: 'response' as 'response' });

  }
}
