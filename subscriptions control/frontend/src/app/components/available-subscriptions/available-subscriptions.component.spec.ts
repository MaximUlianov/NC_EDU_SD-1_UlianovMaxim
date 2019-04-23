import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {AvailableSubscriptionsComponent} from './available-subscriptions.component';

describe('AvailableSubscriptionsComponent', () => {
  let component: AvailableSubscriptionsComponent;
  let fixture: ComponentFixture<AvailableSubscriptionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AvailableSubscriptionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AvailableSubscriptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
