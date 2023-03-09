import { RadarVisualizationConfig } from 'src/app/model/radar-visualization-config';

export const radar_visualization_config: RadarVisualizationConfig = {
  svg_id: 'radar',
  width: 1450,
  height: 1000,
  colors: {
    background: '#fff',
    grid: '#bbb',
    inactive: '#ddd',
  },
  title: 'Allgeier Technology Radar',
  quadrants: [],
  rings: [],
  print_layout: true,
  links_in_new_tabs: true,
  entries: [],
};
