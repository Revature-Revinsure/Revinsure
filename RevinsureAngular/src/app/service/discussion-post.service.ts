import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { DiscussionPost } from '../models/discussion-post';
import { Observable } from 'rxjs';
import { DiscussionReply } from '../models/discussion-reply';
import { DataService } from './data.service';

@Injectable({
  providedIn: 'root'
})
export class DisscussionPostService {

  constructor(private httpClient: HttpClient, private dataService: DataService) { }

  getAllReplies(): Observable<HttpResponse<DiscussionReply[]>> {
    let addOnUrl: string = '/get/response';
    let fullUrl: string = this.dataService.baseURL + addOnUrl;

    return this.httpClient.post<DiscussionReply[]>("http://localhost:8000/discussion/get/response", {
      "id": this.dataService.currentPost.id,
      "title": this.dataService.currentPost.title,
      "content": this.dataService.currentPost.content,
      "dateSubmitted": this.dataService.currentPost.dateSubmitted
    }
      , { withCredentials: true, observe: 'response' as 'response' });

  }

}
