import { TestBed } from '@angular/core/testing';

import { DisscussionBoardService } from './disscussion-board.service';

describe('DisscussionBoardService', () => {
  let service: DisscussionBoardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DisscussionBoardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
