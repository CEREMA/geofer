import { LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { OAuthModule, OAuthStorage } from 'angular-oauth2-oidc';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { SharedModule } from './layout/shared/shared.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { MyStorageService } from './layout/shared/core/storage/MyStorageService';
import { MyLocalStorageService } from './layout/shared/core/storage/MyLocalStorageService';
import { environment } from './environments/environment';
import { HttpInterceptorProviders } from './layout/shared/core/interceptor';
import { registerLocaleData } from '@angular/common';
import localeFr from '@angular/common/locales/fr';
import { ApplicationConfigService } from './layout/shared/core/config/application-config.service';
import { RouterModule } from '@angular/router';

import { ErrorHandler } from '@angular/core';
import { GlobalErrorHandler } from './layout/error.handler';

import {
  DialogService,
  DynamicDialogModule,
  DynamicDialogRef,
} from 'primeng/dynamicdialog';
import { AccordionModule } from 'primeng/accordion';
import { TableModule } from 'primeng/table';
import {
  TranslateLoader,
  TranslateModule,
  TranslateService,
} from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { ChartModule } from 'primeng/chart';

registerLocaleData(localeFr, 'fr');

@NgModule({
  declarations: [],
  exports: [],
  imports: [
    RouterModule,
    TableModule,
    FormsModule,
    ChartModule,
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    DynamicDialogModule,
    AccordionModule,
    SharedModule,
    OAuthModule.forRoot({
      resourceServer: {
        allowedUrls: environment.allowedUrls,
        sendAccessToken: true,
      },
    }),
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient],
      },
    }),
  ],
  providers: [
    { provide: ErrorHandler, useClass: GlobalErrorHandler },
    DialogService,
    MyLocalStorageService,
    DynamicDialogRef,
    {
      provide: OAuthStorage,
      useClass: MyStorageService,
    },
    HttpInterceptorProviders,
    {
      provide: LOCALE_ID,
      useFactory: () => {},
      deps: [],
    },
    {
      provide: LOCALE_ID,
      useFactory: (translate: TranslateService) => {
        return translate.getBrowserLang();
      },
      deps: [TranslateService],
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {
  constructor(applicationConfigService: ApplicationConfigService) {
    applicationConfigService.endpointPrefix = environment.serverApiUrl;
  }
}

export function HttpLoaderFactory(http: HttpClient): TranslateHttpLoader {
  return new TranslateHttpLoader(http, 'assets/i18n/');
}
