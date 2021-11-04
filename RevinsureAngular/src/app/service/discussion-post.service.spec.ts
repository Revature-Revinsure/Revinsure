import { TestBed } from '@angular/core/testing';

import { DiscussionPostService } from './discussion-post.service';

describe('DisscussionPostService', () => {
  let service: DiscussionPostService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DiscussionPostService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
