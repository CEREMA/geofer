import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApplicationConfigService } from 'layout/shared/core/config/application-config.service';
import IDepartements from '../interfaces/IDepartements';
@Injectable({
  providedIn: 'root',
})
export class DepartementsService {
  constructor(
    private httpClient: HttpClient,
    private applicationConfigService: ApplicationConfigService
  ) {}
  public lookUp(): Observable<IDepartements[]> {
    return this.httpClient.get<IDepartements[]>(
      this.applicationConfigService.getEndpointFor(
        `/v1/query/lookUpDepartements`
      )
    );
  }
  public getDepartementsById(id: string): Observable<IDepartements[]> {
    return this.httpClient.get<IDepartements[]>(
      this.applicationConfigService.getEndpointFor(
        `/v1/query/searchDepartements?code=${id}`
      )
    );
  }
}
