import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { RootComponent } from './root/root.component';
import { MethodologieComponent } from './methodologie/methodologie.component';
import { LegalComponent } from './legal/legal.component';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: '',
        component: RootComponent,
      },
      {
        path: 'carto',
        component: RootComponent,
      },
      {
        path: 'methodologie',
        component: MethodologieComponent,
      },
      {
        path: 'legal',
        component: LegalComponent,
      },
    ]),
  ],
  exports: [RouterModule],
})
export class HomeRoutingModule {}
