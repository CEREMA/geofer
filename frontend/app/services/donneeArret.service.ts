import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApplicationConfigService } from 'layout/shared/core/config/application-config.service';
import IDonneeArret from 'app/interfaces/IDonneeArret';
@Injectable({
  providedIn: 'root',
})
export class DonneeArretService {
  constructor(
    private httpClient: HttpClient,
    private applicationConfigService: ApplicationConfigService
  ) {}

  public getByGareId(id: number): Observable<IDonneeArret[]> {
    return this.httpClient.get<IDonneeArret[]>(
      this.applicationConfigService.getEndpointFor(
        `/v1/donnee-arret/gare/${id}`
      )
    );
  }
  public getDonneesArret(gareIds: number[]): Observable<IDonneeArret[]> {
    return this.httpClient.get<IDonneeArret[]>(
      this.applicationConfigService.getEndpointFor(
        `/v1/query/donneesArret?gareIds=${gareIds.join(', ')}`
      )
    );
  }
}
