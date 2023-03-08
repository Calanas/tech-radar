import { Quadrant } from './quadrant';
import { Ring } from './ring';
import { Technology } from './technology';
let i = 0;
export const RINGS: Ring[] = [
  { index: i++, name: 'ADOPT', color: '#5ba300' },
  { index: i++, name: 'TRIAL', color: '#009eb0' },
  { index: i++, name: 'ASSESS', color: '#c7ba00' },
  { index: i++, name: 'HOLD', color: '#e09b96' },
];
i = 0;
export const QUADRANTS: Quadrant[] = [
  { index: i++, name: 'Bottom Right' },
  { index: i++, name: 'Bottom Left' },
  { index: i++, name: 'Top Left' },
  { index: i++, name: 'Top Right' },
];
export const TECHNOLOGIES: Technology[] = [
  {
    moved: -1,
    label: 'Spring',
    ring: RINGS[2],
    quadrant: QUADRANTS[3],
  },
  {
    moved: -1,
    label: 'Angular',
    ring: RINGS[2],
    quadrant: QUADRANTS[3],
  },
  {
    moved: 1,
    label: 'Java',
    ring: RINGS[0],
    quadrant: QUADRANTS[0],
  },
  {
    moved: 0,
    label: 'TypeScript',
    ring: RINGS[0],
    quadrant: QUADRANTS[0],
  },
  {
    moved: 1,
    label: 'Firestore',
    ring: RINGS[1],
    quadrant: QUADRANTS[2],
  },
  {
    moved: -1,
    label: 'Ansible',
    ring: RINGS[2],
    quadrant: QUADRANTS[2],
  },
  {
    moved: 0,
    label: 'MariaDB',
    ring: RINGS[1],
    quadrant: QUADRANTS[3],
  },
];
