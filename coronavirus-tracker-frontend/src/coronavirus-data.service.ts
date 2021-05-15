import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CoronavirusDataService {

  private baseUrl = 'http://localhost:8080/coronavirus/';

  constructor(private httpClient: HttpClient) { }

  getCoronavirusData(): Observable<any> {
    return this.httpClient.get(this.baseUrl);
  }
}
