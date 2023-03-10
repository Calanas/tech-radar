import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { Quadrant } from '../model/quadrant';
import { Ring } from '../model/ring';
import { RINGS, QUADRANTS } from '../model/mock-technologies';
import { Technology } from '../model/technology';

@Component({
  selector: 'atr-add-technology',
  templateUrl: './add-technology.component.html',
  styleUrls: ['./add-technology.component.scss'],
})
export class AddTechnologyComponent implements OnInit {
  quadrants?: Quadrant[];
  rings?: Ring[];

  technologyForm: FormGroup;
  constructor(private fb: FormBuilder) {
    this.technologyForm = this.fb.nonNullable.group({
      label: ['', Validators.required],
      ring: this.fb.control<Ring | null>(null, Validators.required),
      quadrant: this.fb.control<Quadrant | null>(null, Validators.required),
      moved: [0, Validators.required],
    });
  }

  ngOnInit() {
    this.getDropdownContents();
  }

  getDropdownContents(): void {
    this.quadrants = QUADRANTS;
    this.rings = RINGS;
  }

  addTechnology(): void {
    const newTech: Technology = this.technologyForm.value as Technology;
    newTech.ringChanged = new Date();
    console.log(newTech);
    //service POST newTech
  }
}
