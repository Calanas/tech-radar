import { Component, OnInit } from '@angular/core';
import { Technology } from '../model/technology';
import { TECHNOLOGIES } from '../model/mock-technologies';
import { RadarVisualizationConfig } from 'src/types/RadarVisualizationConfig';
import { radar_visualization_config } from '../radar-configuration';

declare function radar_visualization(config: RadarVisualizationConfig): void;
@Component({
  selector: 'atr-show-technologies',
  templateUrl: './show-technologies.component.html',
  styleUrls: ['./show-technologies.component.scss'],
})
export class ShowTechnologiesComponent implements OnInit {
  technologies?: Technology[];

  ngOnInit() {
    this.getTechnologies();
    this.insertTechnologies();
  }

  getTechnologies() {
    this.technologies = TECHNOLOGIES;
  }

  insertTechnologies() {
    radar_visualization_config.entries = this.technologies;
    radar_visualization(radar_visualization_config);
  }
}
