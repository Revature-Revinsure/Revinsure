import { Component, OnInit } from '@angular/core';
import { CovidQuestion } from '../models/covid-question';
import { CovidQuestionsService } from '../service/covid-questions.service';

@Component({
  selector: 'app-covid-questions',
  templateUrl: './covid-questions.component.html',
  styleUrls: ['./covid-questions.component.css']
})
export class CovidQuestionsComponent implements OnInit {

  currentDate: number = Date.now();
  message: string | undefined = "";


  constructor(private covidQA: CovidQuestionsService) { }

  ngOnInit(): void {
  }
  haveORnot: boolean = false;
  haveORbeenA: boolean = false;

  covidQ!: CovidQuestion;


  submit() {

    this.covidQ = {
      hasCovid: this.haveORnot,
      aroundCovid: this.haveORbeenA,
      dateAnswered: this.currentDate
    }

    console.log(this.covidQ);

    this.covidQA.submitCovidForm(this.covidQ).subscribe(

      response => {
        this.message = response.body?.message;

        console.log(this.message);
      }

    );

  }


  onChangedB(value: boolean) {
    this.haveORbeenA = value;
    console.log(this.haveORbeenA);
  }


  onChangedA(value: boolean) {
    this.haveORnot = value;

    console.log(this.haveORnot);


  }


}
