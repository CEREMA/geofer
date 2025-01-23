import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApplicationConfigService } from 'layout/shared/core/config/application-config.service';
import IRefTypeIsochrone from 'app/interfaces/IRefTypeIsochrone';

@Injectable({
  providedIn: 'root',
})
export class RefTypeIsochrone {
  constructor(
    private httpClient: HttpClient,
    private applicationConfigService: ApplicationConfigService
  ) {}

  public get(): Observable<IRefTypeIsochrone[]> {
    return this.httpClient.get<IRefTypeIsochrone[]>(
      this.applicationConfigService.getEndpointFor(`/v1/ref-type-isochrone`)
    );
  }
}
