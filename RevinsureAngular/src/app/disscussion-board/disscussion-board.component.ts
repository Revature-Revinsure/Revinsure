import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DiscussionPost } from '../models/discussion-post';
import { DataService } from '../service/data.service';
import { DisscussionBoardService } from '../service/discussion-board.service';

@Component({
  selector: 'app-disscussion-board',
  templateUrl: './disscussion-board.component.html',
  styleUrls: ['./disscussion-board.component.css']
})
export class DisscussionBoardComponent implements OnInit {

  public postList: DiscussionPost[] = [];

  constructor(private formBuilder: FormBuilder, private discussionBoardService: DisscussionBoardService, private dataService: DataService, private router: Router) { }




  postForm = this.formBuilder.group({
    postTitle: ["", Validators.required],
    postBody: ["", Validators.required],
  });


  ngOnInit(): void {
    this.getDiscussionPosts();

    console.log(this.postList);
  }

  getDiscussionPosts() {
    this.discussionBoardService.getAllPosts().subscribe(
      (response) => {
        this.postList = response;
        console.log(response);
        console.log(this.postList);

      }
    );

  }

  selectPost(post: DiscussionPost) {
    console.log(post);
    this.dataService.currentPost = post;
    this.router.navigate(['/discussion-post']);
  }

  //sortPage(){}

  createPost() {
    let post: DiscussionPost = {
      id: -1,
      title: this.postForm.value.postTitle,
      content: this.postForm.value.postBody,
      dateSubmitted: "",
    }

    console.log(post);

    this.discussionBoardService.createPost(post).subscribe(
      response => {
        this.getDiscussionPosts();
      }
    )
  }


}