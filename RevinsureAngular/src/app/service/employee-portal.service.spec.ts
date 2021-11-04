import { TestBed } from '@angular/core/testing';

import { EmployeePortalService } from './employee-portal.service';

describe('EmployeePortalService', () => {
  let service: EmployeePortalService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmployeePortalService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
