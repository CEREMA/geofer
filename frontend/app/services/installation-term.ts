import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApplicationConfigService } from 'layout/shared/core/config/application-config.service';
import IInstallationTerm from 'app/interfaces/IInstallationTerm';
@Injectable({
  providedIn: 'root',
})
export class InstallationTermService {
  constructor(
    private httpClient: HttpClient,
    private applicationConfigService: ApplicationConfigService
  ) {}

  public getAll(): Observable<IInstallationTerm[]> {
    return this.httpClient.get<IInstallationTerm[]>(
      this.applicationConfigService.getEndpointFor(`/v1/installation-term`)
    );
  }
  public getITEDetails(id: number): Observable<IInstallationTerm> {
    return this.httpClient.get<IInstallationTerm>(
      this.applicationConfigService.getEndpointFor(
        `/v1/query/search?p_installation_term_id=${id}`
      )
    );
  }
}
