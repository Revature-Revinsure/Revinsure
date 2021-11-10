import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {CovidQuestion} from '../models/covid-question';
import { CovidQuestionsService } from '../service/covid-questions.service';
import { DataService } from '../service/data.service';


@Component({
  selector: 'app-covid-questions',
  templateUrl: './covid-questions.component.html',
  styleUrls: ['./covid-questions.component.css']
})
export class CovidQuestionsComponent implements OnInit {

  currentDate: number = Date.now();
  message: string | undefined = "";

  
  constructor(private covidService: CovidQuestionsService,
    private dataService: DataService,
    private router: Router) { }

  ngOnInit(): void {
  }
  haveORnot:boolean = false;
  haveORbeenA:boolean =false;

  covidQ!:CovidQuestion;


  submit(){
    
    this.covidQ ={hasCovid: this.haveORnot,
              aroundCovid: this.haveORbeenA,
              dateAnswered: this.currentDate
            }
            console.log(this.covidQ);

    return this.covidService.submitCovidForm(this.covidQ).subscribe(
      response => {
        this.message = response.body?.message;
        console.log(this.message);

        if(this.dataService.currentUser.type == "EMPLOYEE"){
          this.router.navigate(['/employeeHome']);
          } 
          else{
            this.router.navigate(['/patientHome']);
          }

      }
    )
  
  }

 
onChangedB(value:boolean){
  this.haveORbeenA = value;
  console.log(this.haveORbeenA);
}

 
  onChangedA(value:boolean){
    this.haveORnot = value;
   
      console.log(this.haveORnot);


  }


}
