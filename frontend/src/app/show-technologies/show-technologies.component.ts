import { Component, OnInit } from '@angular/core';
import { Technology } from '../model/technology';
import { RadarVisualizationConfig } from 'src/app/model/radar-visualization-config';
import { radar_visualization_config } from '../radar-configuration';
import { Quadrant } from '../model/quadrant';
import { Ring } from '../model/ring';
import { FirestoreService } from '../firestore.service';
import { combineLatest } from 'rxjs';

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

  constructor(private firestoreService: FirestoreService) {}

  ngOnInit() {
    const technologies$ = this.firestoreService.getTechnologies();
    const rings$ = this.firestoreService.getRings();
    const quadrants$ = this.firestoreService.getQuadrants();

    combineLatest([technologies$, rings$, quadrants$]).subscribe(
      ([technologies, rings, quadrants]) => {
        this.technologies = technologies;
        this.quadrants = quadrants;
        this.rings = rings;
        this.fillConfig();
      }
    );
  }

  fillConfig() {
    radar_visualization_config.entries = this.technologies;
    radar_visualization_config.rings = this.rings;
    radar_visualization_config.quadrants = this.quadrants;
    radar_visualization(radar_visualization_config);
  }
}
