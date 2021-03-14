import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewParkingLotComponent } from './new-parking-lot.component';

describe('NewParkingLotComponent', () => {
  let component: NewParkingLotComponent;
  let fixture: ComponentFixture<NewParkingLotComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewParkingLotComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewParkingLotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
