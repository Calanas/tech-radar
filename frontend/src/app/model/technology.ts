import { Quadrant } from './quadrant';
import { Ring } from './ring';

export interface Technology {
  quadrant: Quadrant;
  label: string;
  ring: Ring;
  moved: number;
}
