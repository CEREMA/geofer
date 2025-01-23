import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApplicationConfigService } from 'layout/shared/core/config/application-config.service';
import ICommunes from '../interfaces/ICommunes';
@Injectable({
  providedIn: 'root',
})
export class CommunesServices {
  constructor(
    private httpClient: HttpClient,
    private applicationConfigService: ApplicationConfigService
  ) {}
  public lookUp(): Observable<ICommunes[]> {
    return this.httpClient.get<ICommunes[]>(
      this.applicationConfigService.getEndpointFor(`/v1/query/lookUpCommunes`)
    );
  }
}
