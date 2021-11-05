import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisscussionBoardComponent } from './disscussion-board.component';

describe('DisscussionBoardComponent', () => {
  let component: DisscussionBoardComponent;
  let fixture: ComponentFixture<DisscussionBoardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisscussionBoardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DisscussionBoardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
