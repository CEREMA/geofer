import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'environments/environment';
import IRegions from 'app/interfaces/IRegions';

@Injectable({
  providedIn: 'root',
})
export class ReferentielServices {
  private apiUrl = environment.config.referentielUri + '/rest/v1';

  constructor(private http: HttpClient) {}

  checkRegion(code: string): Observable<IRegions[]> {
    return this.http.get<IRegions[]>(
      `${this.apiUrl}/dpt_regions?regionCode=eq.${code}`
    );
  }

  getRegionByDpt(code: string): Observable<IRegions[]> {
    return this.http.get<IRegions[]>(
      `${this.apiUrl}/dpt_regions?departmentCode=eq.${code}`
    );
  }
}
