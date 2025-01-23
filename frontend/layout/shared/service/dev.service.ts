import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApplicationConfigService } from '../core/config/application-config.service';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class Dev {
  constructor() {}
}
