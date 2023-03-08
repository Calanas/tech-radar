import { Technology } from './technology';

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
  quadrants: {
    name: string;
  }[];
  rings: {
    name: string;
    color: string;
  }[];
  print_layout: boolean;
  links_in_new_tabs: boolean;
  entries?: Technology[];
}
