import { Component, OnInit } from '@angular/core';
import { Technology } from '../model/technology';
import { QUADRANTS, RINGS } from '../model/mock-technologies';
import { RadarVisualizationConfig } from 'src/app/model/radar-visualization-config';
import { radar_visualization_config } from '../radar-configuration';
import { Quadrant } from '../model/quadrant';
import { Ring } from '../model/ring';
import { FirestoreService } from '../firestore.service';
import { map } from 'rxjs';

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
    const source = this.firestoreService.getTechnologies();
    source
      .pipe(
        map((test) =>
          test.map<Technology>(({ quadrant, moved, ring, label }) => {
            return {
              moved: moved,
              label: label,
              ring: RINGS[ring - 1],
              quadrant: QUADRANTS[quadrant - 1],
            };
          })
        )
      )
      .subscribe((technologies) => {
        console.log(`Got amount of ${technologies.length} technologies`);
        this.technologies = technologies;
        this.quadrants = QUADRANTS;
        this.rings = RINGS;
        this.fillConfig();
      });
  }

  fillConfig() {
    radar_visualization_config.entries = this.technologies;
    radar_visualization_config.rings = this.rings;
    radar_visualization_config.quadrants = this.quadrants;
    radar_visualization(radar_visualization_config);
  }
}
