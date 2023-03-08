import { Technology } from './technology';
import { Quadrant } from './quadrant';
import { Ring } from './ring';

export interface RadarVisualizationConfig {
  svg_id: string;
  width: number;
  height: number;
  colors: {
    background: string;
    grid: string;
    inactive: string;
  };
  title: string;
  quadrants?: Quadrant[];
  rings?: Ring[];
  print_layout: boolean;
  links_in_new_tabs: boolean;
  entries?: Technology[];
}
