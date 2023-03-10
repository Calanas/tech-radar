import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TechnologyBackend } from './model/technology';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class FirestoreService {
  backendUrl?: string = 'http://localhost:8080/api/v1';

  constructor(private http: HttpClient) {}

  getTechnologies(): Observable<TechnologyBackend[]> {
    return this.http.get<TechnologyBackend[]>(
      `${this.backendUrl}/technologies`
    );
  }
}
