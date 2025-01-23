import { Component, OnInit } from '@angular/core';
import { PrimeNGConfig } from 'primeng/api';
import { AuthConfig, OAuthService } from 'angular-oauth2-oidc';
import { environment } from './environments/environment';
import { TranslateService } from '@ngx-translate/core';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  inputStyle = 'outlined';

  ripple!: boolean;

  theme = 'green';

  layoutColor = 'white';

  colorScheme = 'light';

  menuMode = 'slim';

  authConfig: AuthConfig = {
    issuer: environment.orionUrl,
    redirectUri: environment.redirectUri,
    clientId: environment.clientId,
    scope: 'openid profile email',
    responseType: 'code',
    requireHttps: false,
    postLogoutRedirectUri: environment.deconnexionUri,
    // at_hash is not present in id token in older versions of keycloak.
    // use the following property only if needed!
    //disableAtHashCheck: true,
    showDebugInformation: true,
  };

  constructor(
    private primengConfig: PrimeNGConfig,
    private oauthService: OAuthService,
    private translateService: TranslateService
  ) {
    this.translateService.setDefaultLang('fr');
    this.translateService.use(this.translateService.getBrowserLang() as string);
  }

  ngOnInit(): void {
    this.primengConfig.ripple = true;
    this.configure();
  }

  private configure(): void {
    this.oauthService.configure(this.authConfig);
    this.oauthService.loadDiscoveryDocumentAndTryLogin();
    this.oauthService.setupAutomaticSilentRefresh();
  }
}
