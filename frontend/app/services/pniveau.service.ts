import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApplicationConfigService } from 'layout/shared/core/config/application-config.service';
import IPassageNiveau from 'app/interfaces/IPassageNiveau';
@Injectable({
  providedIn: 'root',
})
export class PNiveauService {
  constructor(
    private httpClient: HttpClient,
    private applicationConfigService: ApplicationConfigService
  ) {}

  public get(): Observable<IPassageNiveau[]> {
    return this.httpClient.get<IPassageNiveau[]>(
      this.applicationConfigService.getEndpointFor(`/v1/passage-niveau`)
    );
  }
}
