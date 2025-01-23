import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApplicationConfigService } from 'layout/shared/core/config/application-config.service';
import IReseauFerroviaire from 'app/interfaces/IReseauFerroviaire';

@Injectable({
  providedIn: 'root',
})
export class ReseauFerroviaireService {
  constructor(
    private httpClient: HttpClient,
    private applicationConfigService: ApplicationConfigService
  ) {}

  public get(): Observable<IReseauFerroviaire[]> {
    return this.httpClient.get<IReseauFerroviaire[]>(
      this.applicationConfigService.getEndpointFor(`/v1/reseau-ferroviaire`)
    );
  }
}
