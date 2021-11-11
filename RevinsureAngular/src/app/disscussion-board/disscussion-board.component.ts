import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { DiscussionPost } from '../models/discussion-post';
import { DisscussionBoardService } from '../service/discussion-board.service';

@Component({
  selector: 'app-disscussion-board',
  templateUrl: './disscussion-board.component.html',
  styleUrls: ['./disscussion-board.component.css']
})
export class DisscussionBoardComponent implements OnInit {

  public postList: DiscussionPost[] = [];

  public singlePost: DiscussionPost = {id: 1, 
                                      title:  "Discussion Post Title",
                                      postBody: "Discussion Post Body",
                                      dateOfPost: "1/5/1999",
                                      //opName: "op@email.com"
                                    }

  public anotherPost: DiscussionPost = {id: 2, 
                                      title:  "Another Discussion Post Title",
                                      postBody: "Another Discussion Post Body",
                                      dateOfPost: "5/7/2021",
                                      //opName: "op2@email.com"
                                    }


  constructor(private formBuilder: FormBuilder,private discussionBoardService: DisscussionBoardService) { }


 

  postForm = this.formBuilder.group({
    postTitle: ["", Validators.required],
    postBody: ["", Validators.required],
  });


  ngOnInit(): void {
    // //this.getDiscussionPosts();
    this.postList[0] = this.singlePost;
    this.postList[1] = this.anotherPost;

    console.log(this.postList);
  }

  getDiscussionPosts(){
    this.discussionBoardService.getAllPosts().subscribe(
      response => this.postList = response
    );

    }

 //sortPage(){}

  createPost(){
    let post: DiscussionPost = {
      id: -1,
      title:  this.postForm.value.postTitle,
      postBody: this.postForm.value.postBody,
      dateOfPost: "",}

    console.log(post);
    
    this.discussionBoardService.createPost(post).subscribe(
    response => {
      console.log(response);
    }
    )
  }


}