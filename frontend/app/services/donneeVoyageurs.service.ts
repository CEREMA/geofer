import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApplicationConfigService } from 'layout/shared/core/config/application-config.service';
import IQDonneeVoyageurByGare from 'app/interfaces/IQDonneeVoyageurByGare';
import IQFrequentationVoyageur from 'app/interfaces/IQFrequentationVoyageur';
@Injectable({
  providedIn: 'root',
})
export class DonneeVoyageursService {
  constructor(
    private httpClient: HttpClient,
    private applicationConfigService: ApplicationConfigService
  ) {}

  public getFrequentationByGare(
    gareIds: number[]
  ): Observable<IQDonneeVoyageurByGare[]> {
    return this.httpClient.get<IQDonneeVoyageurByGare[]>(
      this.applicationConfigService.getEndpointFor(
        `/v1/query/donneeVoyageurByGare?gareIds=${gareIds.join(', ')}`
      )
    );
  }

  public getFrequentationByGares(
    gareIds: number[]
  ): Observable<IQFrequentationVoyageur[]> {
    return this.httpClient.get<IQFrequentationVoyageur[]>(
      this.applicationConfigService.getEndpointFor(
        `/v1/query/frequentationVoyageur?gareIds=${gareIds.join(', ')}`
      )
    );
  }

  public getFrequentation(gareIds: any[]): Observable<any> {
    return this.httpClient.get(
      this.applicationConfigService.getEndpointFor(
        `/v1/query/allVoyageurs?gareIds=${gareIds.join(', ')}`
      )
    );
  }
}
