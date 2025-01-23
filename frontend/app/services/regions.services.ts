import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApplicationConfigService } from 'layout/shared/core/config/application-config.service';
import IRegions from 'app/interfaces/IRegions';
@Injectable({
  providedIn: 'root',
})
export class RegionsServices {
  constructor(
    private httpClient: HttpClient,
    private applicationConfigService: ApplicationConfigService
  ) {}
  public lookUp(): Observable<IRegions[]> {
    return this.httpClient.get<IRegions[]>(
      this.applicationConfigService.getEndpointFor(`/v1/query/lookUpRegions`)
    );
  }
  public getRegionsById(id: String): Observable<IRegions[]> {
    return this.httpClient.get<IRegions[]>(
      this.applicationConfigService.getEndpointFor(
        `/v1/query/regionsByUID?id=${id}`
      )
    );
  }
}
