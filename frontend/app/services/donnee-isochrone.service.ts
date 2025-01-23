import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApplicationConfigService } from 'layout/shared/core/config/application-config.service';
import IDonneeIsochrone from 'app/interfaces/IDonneeIsochrone';
@Injectable({
  providedIn: 'root',
})
export class DonneeIsochroneService {
  constructor(
    private httpClient: HttpClient,
    private applicationConfigService: ApplicationConfigService
  ) {}

  public get(isochroneId: string): Observable<IDonneeIsochrone[]> {
    return this.httpClient.get<IDonneeIsochrone[]>(
      this.applicationConfigService.getEndpointFor(
        `/v1/donnee-isochrone/isochrone/${isochroneId}`
      )
    );
  }

  public getAll(): Observable<any> {
    return this.httpClient.get(
      this.applicationConfigService.getEndpointFor(`/v1/donnee-isochrone`)
    );
  }

  public getStatsByGares(gare_id: any[]): Observable<any> {
    return this.httpClient.get(
      this.applicationConfigService.getEndpointFor(
        `/v1/query/stats?gareIds=${gare_id.join(',')}`
      )
    );
  }
}
