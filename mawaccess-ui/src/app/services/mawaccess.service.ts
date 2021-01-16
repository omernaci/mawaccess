import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MawaccessService {

  private baseUrl = 'http://localhost:8084/api/mawaccess';

  constructor(private http: HttpClient) { }

  getProject(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  getProjectList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

}
