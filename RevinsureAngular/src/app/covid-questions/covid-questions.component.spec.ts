import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CovidQuestionsComponent } from './covid-questions.component';

describe('CovidQuestionsComponent', () => {
  let component: CovidQuestionsComponent;
  let fixture: ComponentFixture<CovidQuestionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CovidQuestionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CovidQuestionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
