import { RadarVisualizationConfig } from 'src/types/RadarVisualizationConfig';

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
  quadrants: [
    { name: 'Bottom Right' },
    { name: 'Bottom Left' },
    { name: 'Top Left' },
    { name: 'Top Right' },
  ],
  rings: [
    { name: 'ADOPT', color: '#5ba300' },
    { name: 'TRIAL', color: '#009eb0' },
    { name: 'ASSESS', color: '#c7ba00' },
    { name: 'HOLD', color: '#e09b96' },
  ],
  print_layout: true,
  links_in_new_tabs: true,
  entries: [],
};
