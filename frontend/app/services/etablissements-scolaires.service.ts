import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApplicationConfigService } from 'layout/shared/core/config/application-config.service';
import IEtablissementScolaire from 'app/interfaces/IEtablissementScolaire';
@Injectable({
  providedIn: 'root',
})
export class EtablissementsScolaires {
  constructor(
    private httpClient: HttpClient,
    private applicationConfigService: ApplicationConfigService
  ) {}

  public get(): Observable<IEtablissementScolaire[]> {
    return this.httpClient.get<IEtablissementScolaire[]>(
      this.applicationConfigService.getEndpointFor(`/v1/etablissement-scolaire`)
    );
  }
}
