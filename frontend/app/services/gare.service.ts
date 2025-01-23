import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApplicationConfigService } from 'layout/shared/core/config/application-config.service';
import IGare from 'app/interfaces/IGare';
import IQGaresByCommune from 'app/interfaces/IQGaresByCommune';
import IQStatsTable from 'app/interfaces/IQStatsTable';
import IQListGare from 'app/interfaces/IQListGare';
@Injectable({
  providedIn: 'root',
})
export class GareService {
  constructor(
    private httpClient: HttpClient,
    private applicationConfigService: ApplicationConfigService
  ) {}

  public get(): Observable<IGare[]> {
    return this.httpClient.get<IGare[]>(
      this.applicationConfigService.getEndpointFor(`/v1/gare`)
    );
  }

  public getGareByCommune(insee: string): Observable<IQGaresByCommune[]> {
    return this.httpClient.get<IQGaresByCommune[]>(
      this.applicationConfigService.getEndpointFor(
        `/v1/query/garesByCommune?insee_commune=${insee}`
      )
    );
  }

  public getStatsTable(gareIds: number[]): Observable<IQStatsTable[]> {
    return this.httpClient.get<IQStatsTable[]>(
      this.applicationConfigService.getEndpointFor(
        `/v1/query/statsTable?gareIds=${gareIds.join(', ')}`
      )
    );
  }

  public getList(gareIds: number[]): Observable<IQListGare[]> {
    return this.httpClient.get<IQListGare[]>(
      this.applicationConfigService.getEndpointFor(
        `/v1/query/listGare?gareIds=${gareIds.join(', ')}`
      )
    );
  }

  public getById(id: number): Observable<IGare> {
    return this.httpClient.get<IGare>(
      this.applicationConfigService.getEndpointFor(`/v1/gare/${id}`)
    );
  }
}
