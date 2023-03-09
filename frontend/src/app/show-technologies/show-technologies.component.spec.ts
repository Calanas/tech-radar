import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowTechnologiesComponent } from './show-technologies.component';

describe('ShowTechnologiesComponent', () => {
  let component: ShowTechnologiesComponent;
  let fixture: ComponentFixture<ShowTechnologiesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ShowTechnologiesComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ShowTechnologiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
