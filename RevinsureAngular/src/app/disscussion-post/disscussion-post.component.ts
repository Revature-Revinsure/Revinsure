import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { DiscussionPost } from '../models/discussion-post';
import { DiscussionReply } from '../models/discussion-reply';
import { DataService } from '../service/data.service';
import { DisscussionPostService } from '../service/discussion-post.service';

@Component({
  selector: 'app-disscussion-post',
  templateUrl: './disscussion-post.component.html',
  styleUrls: ['./disscussion-post.component.css']
})
export class DisscussionPostComponent implements OnInit {
  currentPost!: DiscussionPost;
  currentReplies!: DiscussionReply[];
  constructor(private dataService: DataService, private formBuilder: FormBuilder, private discussionPostService: DisscussionPostService) { }

  ngOnInit(): void {
    this.currentPost = this.dataService.currentPost;
    this.getAllReplies();
  }

  replyForm = this.formBuilder.group({
    replyString: ["", Validators.required]
  })

  getAllReplies() {
    this.discussionPostService.getAllReplies().subscribe(
      (data) => {
        this.currentReplies = data.body!;
      }
    );
  }

  addReply() {
    let reply: DiscussionReply = {
      id: -1,
      content: this.replyForm.value.replyString,

    }

    this.discussionPostService.postReply(reply).subscribe(
      (data) => {
        this.getAllReplies();
      }
    );
  }

}
