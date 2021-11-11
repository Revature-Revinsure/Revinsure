import { TestBed } from '@angular/core/testing';

import { DisscussionPostService } from './discussion-post.service';

describe('DisscussionPostService', () => {
  let service: DisscussionPostService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DisscussionPostService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
