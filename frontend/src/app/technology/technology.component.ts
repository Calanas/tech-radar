import { Component, OnInit } from '@angular/core';
import { Technology } from '../technologies';
import { TechnologyService } from '../technology.service';

@Component({
  selector: 'atr-technology',
  templateUrl: './technology.component.html',
  styleUrls: ['./technology.component.scss'],
})
export class TechnologyComponent implements OnInit {
  technologies?: Technology[];
  constructor(private technologyService: TechnologyService) {}

  ngOnInit(): void {
    this.technologyService
      .getTechnologies()
      .subscribe((techs: Technology[]) => {
        console.log(techs);
        this.technologies = techs;
      });
  }
}
