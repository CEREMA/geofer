import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApplicationConfigService } from 'layout/shared/core/config/application-config.service';
import IIsochrone from 'app/interfaces/IIsochrone';
import IQInfoByIsochrone from 'app/interfaces/IQInfoByIsochrone';
import IQZoomIsochrone from 'app/interfaces/IQZoomIsochrone';
@Injectable({
  providedIn: 'root',
})
export class IsochronesService {
  constructor(
    private httpClient: HttpClient,
    private applicationConfigService: ApplicationConfigService
  ) {}

  public get(gareId: string): Observable<IIsochrone[]> {
    return this.httpClient.get<IIsochrone[]>(
      this.applicationConfigService.getEndpointFor(
        `/v1/isochrone/gare/${gareId}`
      )
    );
  }
  public getAll(): Observable<IIsochrone[]> {
    return this.httpClient.get<IIsochrone[]>(
      this.applicationConfigService.getEndpointFor(`/v1/isochrone`)
    );
  }
  public getIsochroneInfo(gareIds: IQInfoByIsochrone[]): Observable<any> {
    return this.httpClient.get<IQInfoByIsochrone[]>(
      this.applicationConfigService.getEndpointFor(
        `/v1/query/infoByIsochrone?gareIds=${gareIds.join(', ')}`
      )
    );
  }
  public loadIsochrones(gareIds: IQZoomIsochrone[]): Observable<any> {
    return this.httpClient.get<IQZoomIsochrone[]>(
      this.applicationConfigService.getEndpointFor(
        `/v1/query/zoomIsochrone?gareIds=${gareIds.join(', ')}`
      )
    );
  }
}
