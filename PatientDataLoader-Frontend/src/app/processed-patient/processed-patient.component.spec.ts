import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcessedPatientComponent } from './processed-patient.component';

describe('ProcessedPatientComponent', () => {
  let component: ProcessedPatientComponent;
  let fixture: ComponentFixture<ProcessedPatientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProcessedPatientComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProcessedPatientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
