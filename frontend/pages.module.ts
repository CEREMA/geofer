import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TranslateModule } from '@ngx-translate/core';

import { HomeRoutingModule } from './app/pages/pages-routing.module';
import { SharedModule } from './layout/shared/shared.module';

import { VariablesGlobales } from 'layout/shared/utils/globals';

/** primeng */
import { InputTextareaModule } from 'primeng/inputtextarea';
import { InputTextModule } from 'primeng/inputtext';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { TabViewModule } from 'primeng/tabview';
import { StepsModule } from 'primeng/steps';
import { CalendarModule } from 'primeng/calendar';
import { DropdownModule } from 'primeng/dropdown';
import { InputNumberModule } from 'primeng/inputnumber';
import { KeyFilterModule } from 'primeng/keyfilter';
import { BreadcrumbModule } from 'primeng/breadcrumb';
import { ChartModule } from 'primeng/chart';
import { DialogModule } from 'primeng/dialog';
import { SkeletonModule } from 'primeng/skeleton';
import { MessagesModule } from 'primeng/messages';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { SelectButtonModule } from 'primeng/selectbutton';
import { CardModule } from 'primeng/card';
import { PaginatorModule } from 'primeng/paginator';
import { ListboxModule } from 'primeng/listbox';
import { SidebarModule } from 'primeng/sidebar';
import { PanelModule } from 'primeng/panel';
import { RadioButtonModule } from 'primeng/radiobutton';
import { MultiSelectModule } from 'primeng/multiselect';
import { AutoCompleteModule } from 'primeng/autocomplete';

import { LookupInputComponent } from 'layout/shared/components/lookup-input/lookup-input.component';

@NgModule({
  exports: [],
  providers: [MessageService, VariablesGlobales],
  declarations: [LookupInputComponent],
  imports: [
    BreadcrumbModule,
    RadioButtonModule,
    PanelModule,
    AutoCompleteModule,
    MultiSelectModule,
    SidebarModule,
    ListboxModule,
    PaginatorModule,
    SelectButtonModule,
    ToastModule,
    DialogModule,
    ChartModule,
    InputTextareaModule,
    DropdownModule,
    CalendarModule,
    MessagesModule,
    StepsModule,
    TabViewModule,
    ButtonModule,
    TableModule,
    KeyFilterModule,
    CommonModule,
    HomeRoutingModule,
    SkeletonModule,
    InputTextModule,
    TranslateModule,
    SharedModule,
    InputNumberModule,
    CardModule,
  ],
})
export class HomeModule {}
