import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { LoginGuard } from './layout/shared/core/guard/login.guard';
import { AccueilResolver } from './layout/shared/core/resolver/accueil.resolver';
import { Error403Component } from 'layout/common/err-403/err-403';
import { Error404Component } from 'layout/common/err-404/err-404';
import { Error500Component } from 'layout/common/err-500/err-500';
@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(
      [
        {
          path: 'login',
          loadChildren: () =>
            import('./layout/shared/components/auth/login/login.module').then(
              (m) => m.LoginModule
            ),
        },
        {
          path: 'deconnexion',
          loadChildren: () =>
            import('./layout/shared/components/auth/logout/logout.module').then(
              (m) => m.LogoutModule
            ),
        },
        {
          path: '403',
          data: { breadcrumb: '403 Error' },
          component: Error403Component,
        },
        {
          path: '404',
          data: { breadcrumb: '404 Error' },
          component: Error404Component,
        },
        {
          path: '500',
          data: { breadcrumb: '500 Error' },
          component: Error500Component,
        },
        {
          path: '',
          //canActivate: [LoginGuard],
          loadChildren: () =>
            import('./layout/main.module').then((m) => m.MainModule),
          //resolve: { user: AccueilResolver },
        },
        {
          path: '**',
          component: Error404Component,
        },
      ],
      { scrollPositionRestoration: 'enabled' }
    ),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {}
