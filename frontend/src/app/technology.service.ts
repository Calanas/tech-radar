import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Technology } from './technologies';

@Injectable({
  providedIn: 'root',
})
// Use the technology service to get data from the backend by calling spring boot APIs
export class TechnologyService {
  private baseUrl = 'http://localhost:8080/api/technologies';

  constructor(private httpClient: HttpClient) {}

  getTechnologies(): Observable<Technology[]> {
    return this.httpClient.get<Technology[]>(`${this.baseUrl}`);
  }
}
