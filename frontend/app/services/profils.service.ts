import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApplicationConfigService } from 'layout/shared/core/config/application-config.service';

@Injectable({
  providedIn: 'root',
})
export class ProfilsService {
  constructor(
    private httpClient: HttpClient,
    private applicationConfigService: ApplicationConfigService
  ) {}
  public updateProfil(profil: any): Observable<any> {
    return this.httpClient.post(
      this.applicationConfigService.getEndpointFor(`/user`),
      profil
    );
  }
  public get(): Observable<any> {
    return this.httpClient.get(
      this.applicationConfigService.getEndpointFor(`/user`)
    );
  }
}
