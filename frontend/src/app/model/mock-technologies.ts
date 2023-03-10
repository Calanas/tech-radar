import { Quadrant } from './quadrant';
import { Ring } from './ring';
import { Technology } from './technology';
let i = 0;
export const RINGS: Ring[] = [
  { id: i.toString(), index: i++, name: 'ADOPT', color: '#5ba300' },
  { id: i.toString(), index: i++, name: 'TRIAL', color: '#009eb0' },
  { id: i.toString(), index: i++, name: 'ASSESS', color: '#c7ba00' },
  { id: i.toString(), index: i++, name: 'HOLD', color: '#e09b96' },
];
i = 0;
export const QUADRANTS: Quadrant[] = [
  { id: i.toString(), index: i++, name: 'Bottom Right' },
  { id: i.toString(), index: i++, name: 'Bottom Left' },
  { id: i.toString(), index: i++, name: 'Top Left' },
  { id: i.toString(), index: i++, name: 'Top Right' },
];

i = 0;
export const TECHNOLOGIES: Technology[] = [
  {
    id: (i++).toString(),
    moved: -1,
    label: 'Spring',
    ring: RINGS[2],
    quadrant: QUADRANTS[3],
    ringChanged: new Date(),
  },
  {
    id: (i++).toString(),
    moved: -1,
    label: 'Angular',
    ring: RINGS[2],
    quadrant: QUADRANTS[3],
    ringChanged: new Date(),
  },
  {
    id: (i++).toString(),
    moved: 1,
    label: 'Java',
    ring: RINGS[0],
    quadrant: QUADRANTS[0],
    ringChanged: new Date(),
  },
  {
    id: (i++).toString(),
    moved: 0,
    label: 'TypeScript',
    ring: RINGS[0],
    quadrant: QUADRANTS[0],
    ringChanged: new Date(),
  },
  {
    id: (i++).toString(),
    moved: 1,
    label: 'Firestore',
    ring: RINGS[1],
    quadrant: QUADRANTS[2],
    ringChanged: new Date(),
  },
  {
    id: (i++).toString(),
    moved: -1,
    label: 'Ansible',
    ring: RINGS[2],
    quadrant: QUADRANTS[2],
    ringChanged: new Date(),
  },
  {
    id: (i++).toString(),
    moved: 0,
    label: 'MariaDB',
    ring: RINGS[1],
    quadrant: QUADRANTS[3],
    ringChanged: new Date(),
  },
];
