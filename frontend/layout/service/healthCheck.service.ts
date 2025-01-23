import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable, forkJoin } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class HealthCheckService {
  private keycloakUrl = `${environment.orionUrl}/.well-known/openid-configuration`;
  private apiServerUrl = `${environment.allowedUrls[1]}alive`;

  constructor(private http: HttpClient) {}

  isKeycloakAlive(): Observable<boolean> {
    return this.http.get(this.keycloakUrl).pipe(
      map(() => true),
      catchError(() => [false])
    );
  }

  isApiServerAlive(): Observable<boolean> {
    return this.http.get(this.apiServerUrl).pipe(
      map(() => true),
      catchError(() => [false])
    );
  }

  areBothAlive(): Observable<boolean> {
    return forkJoin([this.isKeycloakAlive(), this.isApiServerAlive()]).pipe(
      map((results) => results.every((status) => status === true))
    );
  }
}
