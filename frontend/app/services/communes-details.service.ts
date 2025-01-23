import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApplicationConfigService } from 'layout/shared/core/config/application-config.service';
import IQLookUpCommunesDetails from 'app/interfaces/IQLookUpCommunesDetails';
@Injectable({
  providedIn: 'root',
})
export class CommunesDetailsService {
  constructor(
    private httpClient: HttpClient,
    private applicationConfigService: ApplicationConfigService
  ) {}
  public lookUp(code_insee: String): Observable<IQLookUpCommunesDetails[]> {
    return this.httpClient.get<IQLookUpCommunesDetails[]>(
      this.applicationConfigService.getEndpointFor(
        `/v1/query/lookUpCommunesDetails?insee_code=${code_insee}`
      )
    );
  }
}
