import { TestBed } from '@angular/core/testing';

import { CovidQuestionsService } from './covid-questions.service';

describe('CovidQuestionsService', () => {
  let service: CovidQuestionsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CovidQuestionsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
