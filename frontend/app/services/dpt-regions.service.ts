import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApplicationConfigService } from 'layout/shared/core/config/application-config.service';
import IQDeptByRegion from 'app/interfaces/IQDeptByRegion';
import IQRegionByDept from 'app/interfaces/IQRegionByDept';
@Injectable({
  providedIn: 'root',
})
export class DptRegionsServices {
  constructor(
    private httpClient: HttpClient,
    private applicationConfigService: ApplicationConfigService
  ) {}
  public checkRegion(code: String): Observable<IQDeptByRegion[]> {
    return this.httpClient.get<IQDeptByRegion[]>(
      this.applicationConfigService.getEndpointFor(
        `/v1/query/deptByRegion?code=${code}`
      )
    );
  }
  public getRegionByDpt(code: String): Observable<IQRegionByDept[]> {
    return this.httpClient.get<IQRegionByDept[]>(
      this.applicationConfigService.getEndpointFor(
        `/v1/query/regionByDept?code=${code}`
      )
    );
  }
}
