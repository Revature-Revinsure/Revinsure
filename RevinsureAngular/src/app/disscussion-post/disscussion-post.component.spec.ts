import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisscussionPostComponent } from './disscussion-post.component';

describe('DisscussionPostComponent', () => {
  let component: DisscussionPostComponent;
  let fixture: ComponentFixture<DisscussionPostComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisscussionPostComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DisscussionPostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
