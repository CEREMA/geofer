import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApplicationConfigService } from 'layout/shared/core/config/application-config.service';
import IDonneeVoyageur from 'app/interfaces/IDonneeVoyageur';
@Injectable({
  providedIn: 'root',
})
export class FrequentationService {
  constructor(
    private httpClient: HttpClient,
    private applicationConfigService: ApplicationConfigService
  ) {}

  public getByGareId(id: number): Observable<IDonneeVoyageur[]> {
    return this.httpClient.get<IDonneeVoyageur[]>(
      this.applicationConfigService.getEndpointFor(
        `/v1/donnee-voyageur/gare/${id}`
      )
    );
  }
}
