import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MainComponent } from './layout/main/main.component';
import { WorkInProgressComponent } from './layout/common/work-in-progress/work-in-progress.component';
import { UIGuard } from 'layout/shared/core/guard/ui.guard';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild([
      {
        path: '',
        component: MainComponent,
        children: [
          {
            path: '',
            redirectTo: 'pages',
            pathMatch: 'full',
          },
          {
            path: 'work-in-progress',
            data: { breadcrumb: 'Work in progress' },
            component: WorkInProgressComponent,
          },
          {
            path: 'pages',
            data: { breadcrumb: 'home' },
            //canActivate: [UIGuard],
            loadChildren: () =>
              import('./pages.module').then((m) => m.HomeModule),
          },
          {
            path: 'dev',
            //canActivate: [UIGuard],
            data: { breadcrumb: 'dev' },
            loadChildren: () =>
              import('./layout/shared/dev/dev.module').then((m) => m.DevModule),
          },
        ],
      },
    ]),
  ],
  exports: [RouterModule],
})
export class MainRoutingModule {}
