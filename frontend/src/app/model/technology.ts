import { Quadrant } from './quadrant';
import { Ring } from './ring';

export interface Technology {
  id: string;
  quadrant: Quadrant;
  label: string;
  ring: Ring;
  moved: number;
  ringChanged: Date;
}
