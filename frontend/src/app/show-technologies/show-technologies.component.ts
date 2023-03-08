import { Component, OnInit } from '@angular/core';
import { Technology } from '../model/technology';
import { QUADRANTS, TECHNOLOGIES, RINGS } from '../model/mock-technologies';
import { RadarVisualizationConfig } from 'src/app/model/radar-visualization-config';
import { radar_visualization_config } from '../radar-configuration';
import { Quadrant } from '../model/quadrant';
import { Ring } from '../model/ring';

declare function radar_visualization(config: RadarVisualizationConfig): void;
@Component({
  selector: 'atr-show-technologies',
  templateUrl: './show-technologies.component.html',
  styleUrls: ['./show-technologies.component.scss'],
})
export class ShowTechnologiesComponent implements OnInit {
  technologies?: Technology[];
  quadrants?: Quadrant[];
  rings?: Ring[];

  ngOnInit() {
    this.getRadarInfo();
    this.fillConfig();
  }

  getRadarInfo() {
    this.technologies = TECHNOLOGIES;
    this.quadrants = QUADRANTS;
    this.rings = RINGS;
  }

  fillConfig() {
    radar_visualization_config.entries = this.technologies;
    radar_visualization_config.rings = this.rings;
    radar_visualization_config.quadrants = this.quadrants;
    radar_visualization(radar_visualization_config);
  }
}
