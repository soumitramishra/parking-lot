import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewParkingLotComponent } from './view-parking-lot.component';

describe('ViewParkingLotComponent', () => {
  let component: ViewParkingLotComponent;
  let fixture: ComponentFixture<ViewParkingLotComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewParkingLotComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewParkingLotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
