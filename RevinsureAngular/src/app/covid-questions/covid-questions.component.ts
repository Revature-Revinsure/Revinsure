import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-covid-questions',
  templateUrl: './covid-questions.component.html',
  styleUrls: ['./covid-questions.component.css']
})
export class CovidQuestionsComponent implements OnInit {

  currentDate: number = Date.now();
  message: string | undefined = "";

  //As a User, I should have to submit a form stating whether I have had, or currently have, covid-19.have uou had it or been exposed
  constructor() { }

  ngOnInit(): void {
  }
  haveORnot:boolean = false;
  haveOrbeenA:boolean =false;

  submit(event:any){
    //make a modle for covid input
  }

  //model have 
  /*
  export interface CovidQuestion {
    hasCovid: boolean,
    aroundCovid: boolean,
    dateAnswered: Date | number;
}


  */

onChangedB(value:boolean){
  this.haveOrbeenA = value;
  console.log(this.haveOrbeenA);
}

  //value:boolean
  onChangedA(value:boolean){
    this.haveORnot = value;
    /*
    let claim: Claim = {id: -1,
      dateOfService: null,
      dateOfClaim: this.currentDate,
      amount: <number>(<unknown>amount),
      status: Status.PENDING,
      description: description};
    */
      console.log(this.haveORnot);

    /*this.claimService.makeClaim(claim).subscribe(
      response => {
        this.message= response.body?.message;

        console.log(this.message);
      }
    );*/

  }


}
