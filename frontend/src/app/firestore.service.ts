import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Technology } from './model/technology';
import { Observable } from 'rxjs';
import { Ring } from './model/ring';

@Injectable({
  providedIn: 'root',
})
export class FirestoreService {
  backendUrl?: string = 'http://localhost:8080/api/v1';

  constructor(private http: HttpClient) {}

  getTechnologies(): Observable<Technology[]> {
    return this.http.get<Technology[]>(`${this.backendUrl}/technologies`);
  }

  getRings(): Observable<Ring[]> {
    return this.http.get<Ring[]>(`${this.backendUrl}/rings`);
  }

  getQuadrants(): Observable<Ring[]> {
    return this.http.get<Ring[]>(`${this.backendUrl}/quadrants`);
  }

  addTechnology(technology: Technology): Observable<Technology> {
    return this.http.post<Technology>(
      `${this.backendUrl}/technologies`,
      technology
    );
  }
}
